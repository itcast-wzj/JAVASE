package com.wzj.day21_网络编程.tcp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 案例需求(跟前面的案例差不多)
 * 客户端：数据来自于键盘录入, 直到输入的数据是886，发送数据结束(就是将之前写死的数据，变得灵活点)
 * 服务端：接收到数据在控制台输出 (这时服务器应该一直启动！)
 * @author wzj
 */
public class TcpClient2 {
    public static void main(String[] args) throws Exception{
        System.out.println("TcpClient2启动......");
        //1.创建客户端套接字
        Socket socket = new Socket("192.168.1.103",10086);

        //2.键盘录入数据
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while ((line=br.readLine()) != null){
            if("886".equals(line)){
                break;
            }
            //3.获取字节输出流发送数据
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(line.getBytes());
        }

        //4.释放资源,(不用close字节输出流,因为它源自socket,关闭socket即可!)
        socket.close();
    }
}
