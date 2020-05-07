package com.yan.netty.netty_init.socket;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedReader;
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
public class BaseSocketServer {


    public final static String OVER = "over";

    public static void main(String[] args) throws IOException, InterruptedException {

        int portNum = 9002;
        // 创建一个新的ServerSocket，用以监听指定端口上的连接请求
        ServerSocket serverSocket = new ServerSocket(portNum);
        Socket socket = serverSocket.accept();

        boolean flag=false;

        while (true) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);

            String requst, response = "";

            while ((requst = in.readLine()) != null) {
                if (OVER.equals(requst)) {
                    flag=true;
                    break;
                }

                response = processRequest(requst);
                printWriter.println(response);

            }





            Thread.sleep(2000);

            if(flag){
                break;
            }
        }


        System.out.println("=====over server====");

    }

    private static String processRequest(String requst) {

        System.out.println("收到请求： " + requst);

        return "收到 收到 ";
    }


}
