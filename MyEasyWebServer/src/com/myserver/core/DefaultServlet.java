package com.myserver.core;

/**
 * @author: dengjingxu
 * @date: 2020/4/18
 * @description: com.myserver.core
 * @version: 1.0
 */
public class DefaultServlet implements Servlet {
    @Override
    public void service(Request request, Response response) {
        response.println("<html>");
        response.println("<head>");
        response.println("<title>");
        response.println("defaultServlet");
        response.println("</title>");
        response.println("</head>");
        response.println("<body>");
        response.println("<h1>");
        response.println("处理静态资源");
        response.println("</h1>");
        response.println("</body>");
        response.println("</html>");
    }
}
