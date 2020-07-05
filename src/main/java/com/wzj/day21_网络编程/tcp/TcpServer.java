package com.wzj.day21_网络编程.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] args) throws IOException {
        System.out.println("TcpServer启动......");
        //1.创建服务器套接字
        ServerSocket serverSocket = new ServerSocket(10086);
        
        //2.监听Socket连接(如果作为服务器,就是看有没有人请求我!!),accept是一个阻塞方法
        Socket socket = serverSocket.accept();

        //3.服务器读取来自客户端的数据
        InputStream inputStream = socket.getInputStream();
        byte[] buf = new byte[1024];
        int len = inputStream.read(buf);
        System.out.println("服务器端接收的数据为:"+new String(buf,0,len));

        //4.服务器给客户端做出响应
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("response: 淘宝页面".getBytes());

        //5.释放资源(同理只需关闭serverSocket即可,因为Socket是ServerSocket产生的,而字节流是Socket产生的,擒贼先擒王)
        serverSocket.close();
    }
}
