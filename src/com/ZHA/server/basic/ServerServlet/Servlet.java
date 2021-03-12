package com.ZHA.server.basic.ServerServlet;

import com.ZHA.server.basic.Request2;
import com.ZHA.server.basic.Response;

import java.io.IOException;

public interface Servlet {
    void service(RequestX request, ResponseX response) throws IOException;
    void doGet(RequestX request, ResponseX response);
    void doPost(RequestX request, ResponseX response);

}
