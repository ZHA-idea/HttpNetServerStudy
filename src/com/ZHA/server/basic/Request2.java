package com.ZHA.server.basic;

import java.io.*;
import java.net.Socket;
import java.util.*;

/**
 * 封装请求协议
 * 获取请求并对其进行分解
 *
 *
 */
public class Request2 {
    private String blank = " ";
    private String CRLF = "\r\n";
    //请求方式、请求url、请求参数
    private String method;
    private String url;
    private String queryStr;
    private String postStr;

    private Map<String, List<String>>paraMap;

    public String requestInfo;

    public Request2 (Socket client) throws IOException {
        this(client.getInputStream());                              //构造器的相互调用
    }
    public Request2 (InputStream is) throws IOException {
        paraMap = new HashMap<String, List<String>>();
        byte[] datas = new byte[1024*1024];
        int len;
        try {
            len = is.read(datas);
            this.requestInfo = new String(datas, 0, len);   //字符流到字符串
            parseRequestInfo();//启动分解
            convertMap();//存进Map
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
        System.out.println("method:"+method);
        System.out.println("----获取请求url----");//第一个"/"到HTTP/
        url = requestInfo.substring(requestInfo.indexOf("/")+1,requestInfo.indexOf("HTTP/"));
        System.out.println("url:"+url);
        System.out.println("----获取get----");//可能有GET的？
        int queryIdx = this.url.indexOf("?");
        if (queryIdx >= 0){
            queryStr = url.substring(queryIdx+1).trim();
            System.out.println("getStr:"+queryStr);
        }else{
            System.out.println("null");
        }

        System.out.println("----获取post----");//也可能有POST的
        postStr = this.requestInfo.substring(requestInfo.lastIndexOf(CRLF)).trim();//(trim函数用来清除多余的换行符、空格等
        System.out.println("postStr:"+postStr);
        if (postStr != null){
            queryStr += "&"+postStr;
        }
        System.out.println("----query----");
        System.out.println("query:"+queryStr);
        System.out.println();
    }
    //todo:数据放进map
    private void convertMap() throws UnsupportedEncodingException {
        /**
         * 此时的query是这样的：
         * aaa=111&bbb=222&others=
         *
         */
        //1. 分割字符串
        String[] keyValues = this.queryStr.split("&");/**将 queryStr 以 & 为分界断开放进 keyValues 数组里*/
        for(String Str:keyValues){
            String[] kv = Str.split("=");
            kv = Arrays.copyOf(kv,2);/**这一步把kv强制变成了两个长度的字符串数组，飞常巧妙,如果原本无参数则填补null*/
            String key = kv[0];
            String value = kv[1];
            System.out.println(kv[0]+"--->"+kv[1]);
            if(value!=null){
                value = decodeChinese(value,"UTF-8");
                System.out.println("----->"+value);
            }
            if (!paraMap.containsKey(key)) {
                paraMap.put(key, new ArrayList<String>());
            }
            paraMap.get(key).add(value);
        }
    }

    public String[] getParaValues(String key){
        List<String> values = this.paraMap.get(key);
        if (values == null || values.size() < 1){
            return null;
        }else{
            return values.toArray(new String[0]);
        }
    }

    public String getParameter(String key){
        String[] values = getParaValues(key);
        return values == null?null:values[0];
    }

    private String decodeChinese(String value,String enc) throws UnsupportedEncodingException {
        try {
            return java.net.URLDecoder.decode(value, enc);
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return null;
    }


}
