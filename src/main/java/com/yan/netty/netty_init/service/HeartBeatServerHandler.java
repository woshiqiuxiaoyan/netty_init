package com.yan.netty.netty_init.service;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
@Slf4j
@ChannelHandler.Sharable
public class HeartBeatServerHandler extends ChannelInboundHandlerAdapter {
    private int lossConnectCount = 0;

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("已经5秒未收到客户端的消息了！");
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;
            if (event.state()== IdleState.READER_IDLE){
                lossConnectCount++;
                if (lossConnectCount>2){
                    System.out.println("关闭这个不活跃通道！");
                    ctx.channel().close();
                }
            }
        }else {
            super.userEventTriggered(ctx,evt);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        lossConnectCount = 0;
        ByteBuf buf= (ByteBuf) msg;
        String result=buf.toString(Charset.forName("utf-8"));
        log.info("收到服务内容：{}",buf.toString(Charset.forName("utf-8")));
        //将收到的消息返回给发送者
        ctx.write(Unpooled.copiedBuffer("我是服务端生产的消息： "+result, CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}