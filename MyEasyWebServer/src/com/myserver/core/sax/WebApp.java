package com.myserver.core.sax;

import com.myserver.core.Servlet;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * @author: dengjingxu
 * @date: 2020/4/17
 * @description: com.myserver.core.sax
 * @version: 1.0
 */
public class WebApp {
    private static WebContext webContext;
    static {
        SAXParserFactory saxParserFactory=SAXParserFactory.newInstance();
        PHandler pHandler=new PHandler();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(Thread.currentThread()
                    .getContextClassLoader()
                    .getResourceAsStream("com/myserver/web.xml")
                    ,pHandler);
            webContext=new WebContext(pHandler.getEnityList(),pHandler.getMappingList());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Servlet gerServletFromUrl(String url){
        String classPath=webContext.getClassPathByUrl(url);
        if(null==classPath){
            return null;
        }
        Class clz;
        try {
           clz=Class.forName(classPath);
           return (Servlet) clz.newInstance();
        } catch (ClassNotFoundException e) {
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }
}
