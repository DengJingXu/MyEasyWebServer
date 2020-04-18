package com.myserver.core;

/**
 * @author: dengjingxu
 * @date: 2020/4/17
 * @description: com.myserver.core
 * @version: 1.0
 */
public interface Servlet {
    void service(Request request, Response response);
}
