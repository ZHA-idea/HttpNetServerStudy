package com.ZHA.server.basic;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 *
 *对Server01 response部分的封装
 *
 */
public class Server03 {
    int len;
    ServerSocket serverSocket;
    public static void main(String[] args) throws IOException {
        Server02 server = new Server02();
        server.start();
        server.receive();
    }
    //todo:启动服务
    public void start(){
        try {
            serverSocket = new ServerSocket(8888);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("服务启动失败");
        }
    }
    //todo:接受连接
    public void receive() throws IOException {
        try {
            Socket client = serverSocket.accept();
            System.out.println("一个客户端连接成功");
            //todo:获取请求协议
            Request request = new Request(client);

            System.out.println(request.requestInfo);

            Response response = new Response(client);
            ////todo:返回报文正文

            response.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
            response.append("<html>");
            response.append("<head>");
            response.append("<title>");
            response.append("登陆成功！");
            response.append("</title>");
            response.append("</head>");
            response.append("<body>");
            response.append("Test Succesful");
            response.append("</body>");
            response.append("</html>");
            response.sentToBowser(200);




        }catch (IOException e){
            e.printStackTrace();
            System.out.println("客户端异常");

        }
    }
}
