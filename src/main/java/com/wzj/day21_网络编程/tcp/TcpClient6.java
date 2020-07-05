package com.wzj.day21_网络编程.tcp;

import java.io.*;
import java.net.Socket;

/**
 *案例需求
 * 客户端：数据来自于文本文件，接收服务器反馈
 * 服务器：接收到的数据写入文本文件，给出反馈，代码用线程进行封装，为每一个客户端开启一个线程
 */
public class TcpClient6 {
    public static void main(String[] args) throws Exception{
        System.out.println("TcpClient6启动......");
        //1.创建客户端套接字
        Socket socket = new Socket("192.168.1.103",10086);

        //2.从本地文件中读取数据
        BufferedReader br = new BufferedReader(new FileReader("App.java"));
        //3.将字节输出流封装成字符输出流发送数据
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        String line;
        while ((line=br.readLine()) != null){
            bw.write(line);
            bw.newLine();
            bw.flush(); //因为BufferedWriter是有缓冲区的，所以每次需要flush一下,不然数据可能无法发送过去!
        }
        //告诉服务器那边,我这边写完了!!
        socket.shutdownOutput();

        //客户端接收服务器响应的内容
        BufferedReader brClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("客户端接收的数据为:"+brClient.readLine());

        //4.释放资源,(不用close字节输出流,因为它源自socket,关闭socket即可!)
        br.close();
        socket.close();
    }
}
