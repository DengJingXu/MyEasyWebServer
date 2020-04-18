package com.myserver.core;

import com.myserver.core.constant.Constant;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;

/**
 * @author: dengjingxu
 * @date: 2020/4/17
 * @description: com.myserver.core
 * @version: 1.0
 */
public class Response {

    private BufferedWriter writer;
    private StringBuilder headInfo;
    private StringBuilder textInfo;
    private int length;
    public Response(){
        headInfo=new StringBuilder();
        textInfo=new StringBuilder();
        length=0;
    }
    public Response(OutputStream io){
        this();
        writer=new BufferedWriter(new OutputStreamWriter(io));
    }
    public Response(Socket client) {
        this();
        try {
            writer=new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Response print(String info){
        textInfo.append(info);
        length+=info.getBytes().length;
        return this;
    }
    public Response println(String info){
        textInfo.append("\r\n"+info);
        length+=("\r\n"+info).getBytes().length;
        return this;
    }
    public void createHeadInfo(int code){
        headInfo.append("HTTP/1.1").append(Constant.BLANK);
        headInfo.append(code).append(Constant.BLANK);
        switch (code){
            case 200:
                headInfo.append("OK");
                break;
            case 404:
                headInfo.append("NOT FOUND");
                break;
            case 500:
                headInfo.append("SERVER ERROR");
                break;
            default:
                headInfo.append("UNKNOWN ERROR");
                break;
        }
        headInfo.append(Constant.CRLF);
        headInfo.append("Date:").append(LocalDateTime.now()).append(Constant.CRLF);
        headInfo.append("Server:").append("dengjingxu Server/0.0.1;charset=GBK").append(Constant.CRLF);
        headInfo.append("Content-type:text/html;charset=utf-8").append(Constant.CRLF);
        headInfo.append("Content-length:").append(length).append(Constant.CRLF);
        headInfo.append(Constant.CRLF);
    }
    public void pushToBrowser(int code){
        if(null==headInfo){
            code=500;
        }
        createHeadInfo(code);
        try {
            writer.append(headInfo.toString());
            writer.append(textInfo.toString());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
