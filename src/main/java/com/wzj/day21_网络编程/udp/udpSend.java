package com.wzj.day21_网络编程.udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 案例需求
 * UDP发送数据：数据来自于键盘录入，直到输入的数据是886，发送数据结束
 * UDP接收数据：因为接收端不知道发送端什么时候停止发送，故采用死循环接收
 */
public class udpSend {
    public static void main(String[] args) throws Exception{
        System.out.println("udpSend启动......");
        //1.创建用户数据报套接字
        DatagramSocket ds = new DatagramSocket();

        //2.封装键盘录入数据(没有使用Scanner)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line=br.readLine()) != null){
            if("886".equals(line)){
                break;
            }
            byte[] buf = line.getBytes();
            //3.创建用户数据报包
            DatagramPacket dp = new DatagramPacket(buf,buf.length, InetAddress.getByName("192.168.1.103"),10086);
            //4.发送数据
            ds.send(dp);
        }

        //5.释放资源
        br.close();
        ds.close();
    }
}
