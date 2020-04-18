package com.myserver.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: dengjingxu
 * @date: 2020/4/17
 * @description: com.myserver.core
 * @version: 1.0
 */
public class Server {
    private ServerSocket serverSocket;
    private boolean isRunning;
    public static void main(String[] args) {
        Server server=new Server();
        server.start();
    }
    private void start(){
        try {
            serverSocket=new ServerSocket(8888);
            isRunning=true;
            receive();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void receive(){
        System.out.println("主线程"+Thread.currentThread().getName());
        try {
            while(isRunning){
                System.out.println("等待建立连接");
                Socket  client=serverSocket.accept();
                System.out.println("连接建立"+client);
                new Thread(new Dispatcher(client)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void stop(){
        if(!isRunning){
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
