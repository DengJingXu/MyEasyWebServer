package com.myserver.user;

import com.myserver.core.Request;
import com.myserver.core.Response;
import com.myserver.core.Servlet;

/**
 * @author: dengjingxu
 * @date: 2020/4/18
 * @description: com.myserver.user
 * @version: 1.0
 */
public class LoginServlet implements Servlet {
    @Override
    public void service(Request request, Response response) {
        response.println("<html>");
        response.println("<head>");
        response.println("<title>");
        response.println("登录成功");
        response.println("</title>");
        response.println("</head>");
        response.println("<body>");
        response.println("<h1>");
        response.println("欢迎您:"+request.getParameter("uname"));
        response.println("</h1>");
        response.println("</body>");
        response.println("</html>");
    }
}
