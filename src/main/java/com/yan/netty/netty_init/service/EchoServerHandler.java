package com.yan.netty.netty_init.service;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.nio.charset.Charset;

/**
 * <p>Title:EchoServerHandler </p>
 * <p>Description:</p>
 * Created with IntelliJ IDEA.
 * User: qxy
 * Date: 2019/9/12
 * Time: 13:37
 */
@Slf4j
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {


    /**
     * 对每个传入的消息都要调用
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        super.channelRead(ctx, msg);
        ByteBuf buf= (ByteBuf) msg;
        String result=buf.toString(Charset.forName("utf-8"));
        log.info("收到服务内容：{}",buf.toString(Charset.forName("utf-8")));

//        将收到的消息返回给发送者
        ctx.writeAndFlush(Unpooled.copiedBuffer("我是服务端生产的消息： "+result, CharsetUtil.UTF_8));
    }


    /**
     * 通知 ChannelInboundHandlerAdapter 最后 一次对 channelRead 的调用 是当前批量批量阅读的最后一条消息
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("=====我是服务端 我over 了 ======");

//        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
//                //关闭Channel
//                .addListener(ChannelFutureListener.CLOSE);
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("=====流读取异常>>>>>",cause);
        //关闭Channel
        ctx.close();
    }
}
