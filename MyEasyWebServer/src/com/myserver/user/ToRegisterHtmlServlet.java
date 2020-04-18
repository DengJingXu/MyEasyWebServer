package com.myserver.user;

import com.myserver.core.Request;
import com.myserver.core.Response;
import com.myserver.core.Servlet;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: dengjingxu
 * @date: 2020/4/18
 * @description: com.myserver.user
 * @version: 1.0
 */
public class ToRegisterHtmlServlet implements Servlet {
    @Override
    public void service(Request request, Response response) {
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/myserver/user/register.html");
        byte[] bytes = new byte[1024 * 1024];
        try {
            int len = resourceAsStream.read(bytes);
            response.print(new String(bytes,0,len));
            resourceAsStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
