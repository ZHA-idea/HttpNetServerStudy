package com.ZHA.server.basic;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 封装请求协议
 * 获取请求并对其进行分解
 *
 *
 */
public class Request {
    //请求方式、请求url、请求参数
    private String method;
    private String url;
    private String queryStr;

    public String requestInfo;

    public Request (Socket client) throws IOException {
        this(client.getInputStream());                              //构造器的相互调用
    }
    public Request (InputStream is) throws IOException {
        byte[] datas = new byte[1024*1024];
        int len;
        try {
            len = is.read(datas);
            requestInfo = new String(datas, 0, len);   //字符流到字符串

        }catch (IOException e){
            e.printStackTrace();
            return;
        }
    }
    //todo:分解字符串
    private void parseRequestInfo(){
        System.out.println("----分解开始----");
        System.out.println("----获取请求方式----");//开头到第一个"/"
        method = requestInfo.substring(0,requestInfo.indexOf("/"));//.indexOf()函数 左开右闭
        System.out.println("----获取请求url----");//第一个"/"到HTTP/
        url = requestInfo.substring(requestInfo.indexOf("/")+1,requestInfo.indexOf("HTTP/"));
        
    }





}
