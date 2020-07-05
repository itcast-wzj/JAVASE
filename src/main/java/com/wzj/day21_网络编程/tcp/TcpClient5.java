package com.wzj.day21_网络编程.tcp;

import java.io.*;
import java.net.Socket;

/**
 * 案例需求
 * 客户端：数据来自于文本文件，接收服务器反馈
 * 服务器：接收到的数据写入文本文件，给出反馈
 *
 * TcpClient5:迭代一: 客户端程序和服务器程序都被阻塞了，原因是服务器不知道客户端什么时候写完了!!, 所以没给反馈,而因为没给反馈的，客户端也一直读取不到数据,所以也一直阻塞在哪里!!
 * TcpClient5_1:迭代二: 自定义结束标记, 弊端: 如果上传的文件中有这个标记将导致上传的文件内容不全!
 * TcpClient5_2:迭代三: 使用 socket.shutdownOutput(); 可以代替自定义结束标记!
 *
 * 遇到的问题:
 * Exception in thread "main" java.net.SocketException: Connection reset: 是因为之前在TcpServer5_1中服务器反馈的时候,没加bwServer.newLine()导致的!!
 * https://blog.csdn.net/a718515028/article/details/79078508
 * https://blog.csdn.net/durex0402/article/details/83678883?utm_medium=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-3.nonecase&depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromMachineLearnPai2-3.nonecase
 */
public class TcpClient5 {
    public static void main(String[] args) throws Exception{
        System.out.println("TcpClient5启动......");
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

        //客户端接收服务器响应的内容
        BufferedReader brClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println("客户端接收的数据为:"+brClient.readLine());

        //4.释放资源,(不用close字节输出流,因为它源自socket,关闭socket即可!)
        br.close();
        socket.close();
    }
}
