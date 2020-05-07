package com.yan.netty.netty_init.service;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * <p>Title:EchoHeartBeatChandler </p>
 * <p>Description:</p>
 * Created with IntelliJ IDEA.
 * User: qxy
 * Date: 2020/4/15
 * Time: 17:55
 */
public class EchoHeartBeatChandler extends ChannelInboundHandlerAdapter {


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        System.out.println("===================我是心跳=================");
        super.userEventTriggered(ctx, evt);
    }
}
