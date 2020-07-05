package com.wzj.day21_网络编程.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer6 {
    public static void main(String[] args) throws Exception{
        System.out.println("TcpServer6启动......");
        //1.创建服务器套接字
        ServerSocket serverSocket = new ServerSocket(10086);

        while (true){
            //2.监听Socket连接(如果作为服务器,就是看有没有人请求我!!),accept是一个阻塞方法
            Socket socket = serverSocket.accept();
            new Thread(new ServerThread(socket)).start();
        }

//        serverSocket.close();
    }
}
