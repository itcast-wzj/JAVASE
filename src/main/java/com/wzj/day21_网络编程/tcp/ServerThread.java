package com.wzj.day21_网络编程.tcp;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutionException;

public class ServerThread implements Runnable{

    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bw = new BufferedWriter(new FileWriter("copy.java"));
            //解决文件名冲突的问题
            int count = 0;
            File file = new File("copy["+count+"].java");
            if(file.exists()){ //改成while也行
                count++;
                file = new File("copy["+count+"].java");
            }
           bw = new BufferedWriter(new FileWriter(file));

           String line;
           while((line=br.readLine())!=null){
               bw.write(line);
               bw.newLine();
               bw.flush();
           }

           //服务器给出响应
            BufferedWriter bwServer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bwServer.write("response: 上传文件成功!!!");
            bwServer.newLine();
            bwServer.flush();
       }catch (Exception e){
           e.printStackTrace();
       }finally {
           try {
               if(bw != null)  bw.close();
               if(socket != null)  socket.close();
           }catch (Exception e){
               e.printStackTrace();
           }
       }
    }
}
