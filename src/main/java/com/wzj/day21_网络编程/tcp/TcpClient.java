package com.wzj.day21_网络编程.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 案例需求
 * 客户端：发送数据，接受服务器反馈
 * 服务器：收到消息后给出反馈
 * @author wzj
 */
public class TcpClient {
    public static void main(String[] args) throws Exception {
        System.out.println("TcpClient启动......");
        //1.创建客户端套接字
        Socket socket = new Socket("192.168.1.103",10086);

        //2.获取字节输出流发送数据
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("request: 淘宝".getBytes());

        //3.接收来自服务器的数据
        InputStream inputStream = socket.getInputStream();
        byte[] buf = new byte[1024];
        int len = inputStream.read(buf);
        System.out.println("客户端接收的数据为:"+new String(buf,0,len));

        //3.释放资源,(不用close字节输出流,因为它源自socket,关闭socket即可!)
        socket.close();

        /**
         * Exception in thread "main" java.net.ConnectException: Connection refused: connect
         * 如果是tcp的话,先启动客户端会导致上面的异常!!
         *
         * 因为Tcp协议是要通过三次握手才会建立连接的,直接启动Scoket,是建立不上连接的!
         * 就像你向淘宝服务器发送请求,假如淘宝服务器都不存在,不就404了吗(相当于这里的Connection refused: connect)
         * 注: udp不需要通过三次握手建立连接,所以先启动发送方是不会报错的!
         */
    }
}
