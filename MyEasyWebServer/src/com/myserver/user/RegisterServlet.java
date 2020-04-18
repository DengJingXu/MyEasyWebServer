package com.myserver.user;

import com.myserver.core.Request;
import com.myserver.core.Response;
import com.myserver.core.Servlet;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author: dengjingxu
 * @date: 2020/4/18
 * @description: com.myserver.user
 * @version: 1.0
 */
public class RegisterServlet implements Servlet {
    @Override
    public void service(Request request, Response response) {
        response.println("<html>");
        response.println("<head>");
        response.println("<title>");
        response.println("注册成功");
        response.println("</title>");
        response.println("</head>");
        response.println("<body>");
        response.println("<h1>");
        response.println("欢迎您:"+request.getParameter("uname"));
        response.println("</h1>");
        String[] happies = request.getParameters("happy");
        String hap=null;
        if(null!=happies){
            hap= Arrays.stream(happies).collect(Collectors.joining(","));
        }
        response.println("<p>您的喜好为："+hap+"</p>");
        response.println("</body>");
        response.println("</html>");
    }
}
