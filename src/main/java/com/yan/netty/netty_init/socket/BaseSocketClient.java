package com.yan.netty.netty_init.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * <p>Title:BaseSocket </p>
 * <p>Description:</p>
 * Created with IntelliJ IDEA.
 * User: qxy
 * Date: 2019/9/9
 * Time: 9:16
 */
public class BaseSocketClient {


    public final static String OVER = "over";

    public static void main(String[] args) throws Exception {

        int portNum = 9002;
        String address = "127.0.0.1";

        Socket socket = new Socket(address, portNum);




        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        readLine(in);
        int i = 0;

        do {

            pw.println("hello world");

            Thread.sleep(5000);

            i++;


        } while (i <= 100);

        in.close();
        pw.close();
        socket.close();
        System.out.println("=====over client====");

    }


    private static void readLine(BufferedReader in) {
        Thread thread = new Thread(() -> {
            String requst = "";
            while (true) {
                try {
                    while ((requst = in.readLine()) != null) {
                        System.out.println("====收到 server响应：" + requst);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }


}
