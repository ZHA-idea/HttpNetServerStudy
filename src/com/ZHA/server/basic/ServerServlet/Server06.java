package com.ZHA.server.basic.ServerServlet;

import com.ZHA.server.basic.Request2;
import com.ZHA.server.basic.Response;
import com.ZHA.server.basic.Server03;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * 加入了servlet，解耦了业务代码；
 *
 */
public class Server06 {int len;
    ServerSocket serverSocket;
    public static void main(String[] args) throws IOException {
        Server06 server = new Server06();
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
            RequestX request = new RequestX(client);

//            System.out.println(request.requestInfo);
            //todo：获取响应协议
            ResponseX response = new ResponseX(client);
                //todo:判断发什么 & 返回报文正文
            Servlet servlet = null;
            if (request.getUrl().equals("login")){
                servlet = new LoginServlet();
                servlet.service(request,response);
            }else if (request.getUrl().equals("reg")){
                servlet = new RegisterServlet();
                servlet.service(request,response);
            }else{

            }



            response.sentToBowser(200);




        }catch (IOException e){
            e.printStackTrace();
            System.out.println("客户端异常");

        }
    }

}
