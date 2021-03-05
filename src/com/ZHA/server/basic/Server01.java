package com.ZHA.server.basic;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 *
 *
 *
 */
public class Server01 {
    private ServerSocket serverSocket;
    public static void main(String[] args) throws IOException {
        Server01 server = new Server01();
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
            InputStream is = client.getInputStream();
            byte[] datas = new byte[1024*1024];
            int len = is.read(datas);
            String requestInfo = new String(datas,0,len);
            System.out.println(requestInfo);

            //返回报文正文
            StringBuilder content = new StringBuilder();
            content.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
            content.append("<html>");
            content.append("<head>");
            content.append("<title>");
            content.append("登陆成功！");
            content.append("</title>");
            content.append("</head>");
            content.append("<body>");
            content.append("Test Succesful");
            content.append("</body>");
            content.append("</html>");
            int size = content.toString().getBytes().length;
            StringBuilder responseInfo = new StringBuilder();
            String blank = " ";
            String CRLF = "\r\n";
            //todo:返回协议
            //1.响应状态行
            responseInfo.append("HTTP/1.1").append(blank).append(200).append(blank).append("OK").append(blank);
            //2.响应头
            responseInfo.append("DATE").append(new Date()).append(CRLF);
            responseInfo.append("Server:").append("Server/0.0.1;charset=GBK").append(CRLF);
            responseInfo.append("Content-type:text/html").append(CRLF);
            responseInfo.append("Content-length:").append(size).append(CRLF);
            //3.空行
            responseInfo.append(CRLF);
            //4.正文
            responseInfo.append(content);
            //写出到客户端
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            bw.write(responseInfo.toString());
            bw.flush();
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("客户端异常");

        }
    }


}
