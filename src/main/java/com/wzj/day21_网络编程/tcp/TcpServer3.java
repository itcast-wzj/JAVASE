package com.wzj.day21_网络编程.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer3 {
    public static void main(String[] args) throws Exception{
        System.out.println("TcpServer3启动......");
        //1.创建服务器套接字
        ServerSocket serverSocket = new ServerSocket(10086);

        //2.监听Socket连接(如果作为服务器,就是看有没有人请求我!!),accept是一个阻塞方法
        Socket socket = serverSocket.accept();

        //3.服务器读取来自客户端的数据,并写入到文本文件!(将字节输入流封装成字符输入流: 对于文本的操作字符输入流更加方便,如果是图片,视频则必须使用字节流!)
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new FileWriter("hello.txt")); //文件会创建在该项目下

        String line;
        while((line=br.readLine()) != null){
            bw.write(line);
            bw.newLine();//换行
            bw.flush();
        }

        //4.释放资源(同理只需关闭serverSocket即可,因为Socket是ServerSocket产生的,而字节流是Socket产生的,擒贼先擒王)
        //注:还需关闭bw,因为它并不是serverSocket产生的！
        bw.close();
        serverSocket.close();
    }
}
