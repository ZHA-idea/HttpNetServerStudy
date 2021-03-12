package com.ZHA.server.basic.ServerServlet;

import com.ZHA.server.basic.Request2;
import com.ZHA.server.basic.Response;

import java.io.IOException;

public class RegisterServlet implements Servlet {
    @Override
    public void service(RequestX request, ResponseX response) throws IOException {
        response.append("注册界面");
        response.sentToBowser(200);

    }

    @Override
    public void doGet(RequestX request, ResponseX response) {

    }

    @Override
    public void doPost(RequestX request, ResponseX response) {

    }
}
