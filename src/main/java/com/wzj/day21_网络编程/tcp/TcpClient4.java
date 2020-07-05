package com.wzj.day21_网络编程.tcp;

import java.io.*;
import java.net.Socket;

/**
 * 案例需求(本质是一个上传操作!!)
 * 客户端：数据来自于文本文件
 * 服务器：接收到的数据写入文本文件
 * @author wzj
 */
public class TcpClient4 {
    public static void main(String[] args) throws Exception{
        System.out.println("TcpClient4启动......");
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

        //4.释放资源,(不用close字节输出流,因为它源自socket,关闭socket即可!)
        br.close();
        socket.close();
    }
}
