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
public class Response {
    //todo:返回协议的封装
    private int len;
    private Socket client;
    private StringBuilder responseInfo;
    private StringBuilder body = new StringBuilder();
    private String blank = " ";
    private String CRLF = "\r\n";

    public Response (Socket client){
        this.client = client;
    }
    public void addStatusCode(int statusCode){
        responseInfo.append("HTTP/1.1").append(blank).append(statusCode).append(blank);
        if (statusCode == 200){
            responseInfo.append("OK");
        }else if(statusCode == 404){
            responseInfo.append("NOT FOUND");
        }else if(statusCode == 500){
            responseInfo.append("SERVER ERROR");
        }
        responseInfo.append(blank);
    }
    public void addHead(){
        responseInfo.append("DATE").append(new Date()).append(CRLF);
        responseInfo.append("Server:").append("Server/0.0.1;charset=GBK").append(CRLF);
        responseInfo.append("Content-type:text/html").append(CRLF);
        len = body.toString().length();
        responseInfo.append("Content-length:").append(len).append(CRLF);
        responseInfo.append(CRLF);
    }
    public void sentToBowser(int statusCode) throws IOException {
        responseInfo = new StringBuilder();
        addStatusCode(statusCode);
        addHead();
        responseInfo.append(body);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        bw.write(responseInfo.toString());
        bw.flush();
    }
    public void append(String content){
        body.append(content);
    }

}