package com.yan.netty.netty_init.nioservice;

import com.sun.deploy.config.ClientConfig;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * <p>Title:PlainNioServer </p>
 * <p>Description:</p>
 * Created with IntelliJ IDEA.
 * User: qxy
 * Date: 2020/4/23
 * Time: 15:05
 */
public class PlainNioServer {


    public void server(int port) throws Exception {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        ServerSocket sscoket = serverChannel.socket();
        InetSocketAddress address = new InetSocketAddress(port);
        sscoket.bind(address);
        Selector selector = Selector.open();
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        final ByteBuffer msg = ByteBuffer.wrap("HI\r\n".getBytes());
        for (; ; ) {
            try {
                selector.select();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
            Set<SelectionKey> readyKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = readyKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                iterator.remove();
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) key.channel();
                    //todo 不想写了太长了 40页

                }
            }
        }

    }

}
