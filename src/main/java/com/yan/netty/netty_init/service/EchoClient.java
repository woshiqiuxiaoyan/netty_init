package com.yan.netty.netty_init.service;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.local.LocalAddress;
import io.netty.channel.local.LocalChannel;
import io.netty.channel.local.LocalEventLoopGroup;
import io.netty.util.CharsetUtil;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EchoClient {
    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {

        CountDownLatch countDownLatch=new CountDownLatch(10);

        //     todo  如果是Linux 上用这个比较高效   EpollEventLoopGroup
        EventLoopGroup group = new LocalEventLoopGroup();
        try {    // 创建Bootstrap
            Bootstrap b = new Bootstrap();     // 指定EventLoopGroup以处理客户端事件；需要适用于NIO的实现
            b.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 20000);
            b.group(group)
                       .channel(LocalChannel.class)      // 适用于NIO传输的Channel类型
                    //.remoteAddress(new InetSocketAddress(host, port))     // 设置服务器的InetSocketAddr-ess
                    .handler(new ChannelInitializer<Channel>() {
                        // 在创建Channel时，向ChannelPipeline中添加一个Echo-ClientHandler实例                          须
                        @Override
                        public void initChannel(Channel ch)
                                throws Exception {
                            ch.pipeline()
//                                    .addLast(new IdleStateHandler(0,4,0, TimeUnit.SECONDS))
                                    .addLast(new EchoClientHandler1())
//                                    .addLast(new EchoClientHandler2())
//                                    .addLast(new EchoClientHandler3())
                                    ;
//                                    .addLast(new EchoHeartBeatChandler ());
                        }
                    });

            LocalAddress address=new LocalAddress(host);
            Channel channel = b.connect(address).sync().channel();     // 连接到远程节点，阻塞等待直到连接完成


            Runnable write1=new Runnable() {
                @Override
                public void run() {
                    final ByteBuf buf =Unpooled.copiedBuffer("\n1 号消息\n",CharsetUtil.UTF_8).retain();
                    channel.writeAndFlush(buf.duplicate());
                }
            };


            Executor executor= Executors.newFixedThreadPool(2);

            for (int i=0;i<10;i++){
                executor.execute(write1);
            }



            countDownLatch.await();
            countDownLatch.countDown();

            if (null != channel) {
                channel.closeFuture().sync();      // 阻塞，直到Channel关闭
            }


        } finally {
            group.shutdownGracefully().sync();       //  关闭线程池并且释放所有的资源
        }




    }

    public static void main(String[] args) throws Exception {



        if (args.length != 2) {
            System.err.println(
                    "Usage: " + EchoClient.class.getSimpleName() +
                            " <host> <port>");
            return;
        }



        String host = args[0];
        int port = Integer.parseInt(args[1]);




       /* Thread t1 =  new Thread(() -> {
            try {
                new EchoServer(port).start();    //调用服务器的start()方法
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        t1.start();*/

        new EchoClient(host, port).start();

//       Thread t =  new Thread(() -> {
//            try {
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//
//
//       t.start();


//        try {
//            java.lang.Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


    }
}