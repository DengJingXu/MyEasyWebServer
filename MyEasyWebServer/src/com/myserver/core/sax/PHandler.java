package com.myserver.core.sax;

import com.myserver.core.constant.Constant;
import com.myserver.core.po.Enity;
import com.myserver.core.po.Mapping;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author: dengjingxu
 * @date: 2020/4/17
 * @description: com.myserver.core.sax
 * @version: 1.0
 */
public class PHandler extends DefaultHandler {
    private List<Enity>enityList=new LinkedList<>();
    private List<Mapping> mappingList=new LinkedList<>();
    private Enity enity;
    private Mapping mapping;
    private boolean isServlet;
    private String tag;

    public List<Enity> getEnityList() {
        return enityList;
    }

    public List<Mapping> getMappingList() {
        return mappingList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
       if(Constant.servlet.equals(qName)){
           enity=new Enity();
           isServlet=true;
       }else if(Constant.servletMapping.equals(qName)){
           mapping=new Mapping();
           isServlet=false;
       }
        tag=qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value=new String(ch,start,length).trim();
        if(isServlet){
            if(Constant.servletName.equals(tag)){
                enity.setName(value);
            }else if(Constant.servletClass.equals(tag)){
                enity.setClz(value);
            }
        }else{
            if(Constant.servletName.equals(tag)){
                mapping.setName(value);
            }else if(Constant.urlPattern.equals(tag)){
                mapping.getPatterns().add(value);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(Constant.servlet.equals(qName)){
            enityList.add(enity);
        }else if(Constant.servletMapping.equals(qName)){
            mappingList.add(mapping);
        }
        tag=null;
    }
}
