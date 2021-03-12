package com.ZHA.server.basic.ServerServlet;

import com.ZHA.server.basic.Request2;
import com.ZHA.server.basic.Response;

import java.io.IOException;

public class LoginServlet implements Servlet {
    @Override
    public void service(RequestX request, ResponseX response) throws IOException {
        response.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">");
        response.append("<html>");
        response.append("<head>");
        response.append("<title>");
        response.append("登陆成功！");
        response.append("</title>");
        response.append("</head>");
        response.append("<body>");
        response.append("第一个servlet。你好："+request.getParameter("uname"));
        //System.out.println("--------//"+request.getParameter("uname"));
        response.append("</body>");
        response.append("</html>");
        response.sentToBowser(200);
    }

    @Override
    public void doGet(RequestX request, ResponseX response) {

    }

    @Override
    public void doPost(RequestX request, ResponseX response) {

    }
}
