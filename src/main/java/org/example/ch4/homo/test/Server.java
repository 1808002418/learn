package org.example.ch4.homo.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket socket=new ServerSocket(10086);
        System.out.println("服务端已启动");
        //监听端口返回具体的socket对象
        Socket server = socket.accept();
        //获取输入流
        InputStream inputStream = server.getInputStream();
        //获取输出流
        OutputStream outputStream = server.getOutputStream();

        while (true){
            String line = receiveInfo(inputStream);
            if(line.equals("exit")){
                sendInfo(outputStream,"正在退出..");
                break;
            }
            System.out.println("已接收数据:"+line);
            sendInfo(outputStream,null);
        }
        socket.close();
    }
    public static String receiveInfo(InputStream inputStream){
        byte[] bytes=new byte[1024];
        int len=0;
        try {
            len=inputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(bytes,0,len);
    }
    public static void sendInfo(OutputStream outputStream,String line){
        if (line==null){
            line="已接到数据";
        }
        try {
            outputStream.write(line.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}