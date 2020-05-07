package com.yan.netty.netty_init.service;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable    // 标记该类的实例可以被多个Channel共享
public class EchoClientHandler2 extends
        SimpleChannelInboundHandler<ByteBuf> {



    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.copiedBuffer("=======Netty rocks!=============",   //当被通知Channel是活跃的时候，发送一条消息
        CharsetUtil.UTF_8));
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, //在发生异常时，记录错误并关闭Channel
        Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }



    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
        // 记录已接收消息的转储
        System.out.println(  "===============================EchoClientHandler2 received: \n" + byteBuf.toString(CharsetUtil.UTF_8));

    }
}