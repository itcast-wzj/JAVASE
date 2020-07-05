package com.wzj.day21_网络编程.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class udpReceive {
    public static void main(String[] args) throws Exception{
        System.out.println("udpReceive启动......");
        //1.创建数据报套接字
        DatagramSocket ds = new DatagramSocket(10086);

        while(true){ //模拟接收端一直启动!，所以也不能关闭套接字
            //2.创建接收方数据报包(指定容量)
            byte[] buf = new byte[1024];
            DatagramPacket dp = new DatagramPacket(buf,buf.length);

            //3.使用创建好的接收方数据报包 来接收 发送方发送的数据报包
            ds.receive(dp);

            //4.解析接收方数据报包(因为数据都在该包中)
            System.out.println("数据为:"+new String(dp.getData(),0,dp.getLength()));
        }

        //5.关闭套接字
//        ds.close();
    }
}
