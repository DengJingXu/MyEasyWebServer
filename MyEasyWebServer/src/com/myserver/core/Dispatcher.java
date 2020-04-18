package com.myserver.core;

import com.myserver.core.sax.WebApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author: dengjingxu
 * @date: 2020/4/17
 * @description: com.myserver.core
 * @version: 1.0
 */
public class Dispatcher implements Runnable{

    Socket client;
    private Request request;
    private Response response;

    public Dispatcher(Socket client) {
        try {
            System.out.println(client);
            request=new Request(client);
            response=new Response(client);

        } catch (IOException e) {
            e.printStackTrace();
            release();
        }
        this.client=client;
    }

    @Override
    public void run() {
        String url=request.getUrl();
        try {
            if(null==url||"".equals(url)){
                InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/myserver/index.html");
                indexAnd404(resourceAsStream);
                response.pushToBrowser(200);
                release();
                System.out.println("分发线程终结");
                return;
            }
            Servlet servlet= WebApp.gerServletFromUrl("/"+url);
            if(null==servlet){
                InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/myserver/404.html");
                indexAnd404(resourceAsStream);
                response.pushToBrowser(404);
            }else {
                servlet.service(request,response);
                response.pushToBrowser(200);
            }
            release();
            System.out.println("分发线程"+Thread.currentThread().getName()+"终结"+client);

            return;
        } catch (Exception e) {
            response.println("Server 不妙了");
            response.pushToBrowser(500);
        }
    }

    private void indexAnd404(InputStream resourceAsStream) {
        byte[] bytes = new byte[1024 * 1024];
        int read=0;
        try {
             read= resourceAsStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.print(new String(bytes,0,read));
        try {
            resourceAsStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void release(){
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
