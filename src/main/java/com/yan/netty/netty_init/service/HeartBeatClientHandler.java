package com.yan.netty.netty_init.service;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.util.Date;

@Slf4j
public class HeartBeatClientHandler extends ChannelInboundHandlerAdapter {
    private int lossConnectCount = 0;

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("客户端循环心跳监测发送: "+new Date());
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;
            if (event.state()== IdleState.WRITER_IDLE){
                    ctx.writeAndFlush(Unpooled.copiedBuffer("biubiu",   //当被通知Channel是活跃的时候，发送一条消息
                            CharsetUtil.UTF_8));
            }
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        lossConnectCount = 0;
        ByteBuf buf= (ByteBuf) msg;
        String result=buf.toString(Charset.forName("utf-8"));
        log.info("收到服务内容：{}",result);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}