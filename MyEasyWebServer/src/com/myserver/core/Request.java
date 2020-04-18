package com.myserver.core;

import com.myserver.core.constant.Constant;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.*;

/**
 * @author: dengjingxu
 * @date: 2020/4/17
 * @description: com.myserver.core
 * @version: 1.0
 */
public class Request {

    private String requestInfo;
    private String method;
    private String url;
    private String par;
    private Map<String, List<String>> parametersMap;
    public Request(Socket client) throws IOException {
        this(client.getInputStream());
    }
    public Request(InputStream io){
        byte[] bytes = new byte[1024 * 1024 * 1024];
        try {
            System.out.println("当前建立reuqest的线程"+Thread.currentThread().getName());
            int len = io.read(bytes);
            this.requestInfo=new String(bytes,0,len);
            parametersMap=new HashMap<>();
        } catch (IOException e) {
            e.printStackTrace();
        }
        parseRequestInfo();
    }

    private void parseRequestInfo() {
        method=requestInfo.substring(0,requestInfo.indexOf(" /")).toLowerCase();
        url=requestInfo.substring(requestInfo.indexOf("/")+1,requestInfo.indexOf(" HTTP/"));
        System.out.println(requestInfo);
        int indexParameter=url.indexOf("?");
        if(indexParameter>=0){
            String[] split = url.split("\\?");
            url=split[0];
            par=split[1]==null?null:decode(split[1],"utf-8");
        }else{
            par="";
        }
        if(Constant.POST.equals(method)){
            String tempPar2=requestInfo.substring(requestInfo.lastIndexOf("\r\n")+2,requestInfo.length());
            if(null==par){
                par=tempPar2;
            }else{
                par+="&"+decode(tempPar2,"utf-8");
            }
        }
        String[]strs=par.split("&");
        if(null!=strs&&strs.length>1){
            Arrays.stream(strs).forEach(string->{
                String []str=string.split("=");
                if(str.length>1){
                    String key=str[0];
                    if(null==parametersMap.get(key)){
                        parametersMap.put(key,new ArrayList<>());
                    }
                    parametersMap.get(key).add(str[1]);
                }
            });
        }
    }

    private String decode(String s, String s1) {
        try {
            return java.net.URLDecoder.decode(s,s1);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String[] getParameters(String name){
        List<String> res= parametersMap.get(name);
        return  res==null?null:res.toArray(new String[0]);
    }
    public String getParameter(String name){
        String[] parameters = getParameters(name);
        return parameters==null?null:parameters[0];
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }
}
