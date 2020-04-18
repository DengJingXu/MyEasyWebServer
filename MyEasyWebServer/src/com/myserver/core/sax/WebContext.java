package com.myserver.core.sax;

import com.myserver.core.Servlet;
import com.myserver.core.po.Enity;
import com.myserver.core.po.Mapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: dengjingxu
 * @date: 2020/4/17
 * @description: com.myserver.core.sax
 * @version: 1.0
 */
public class WebContext {
    private Map<String,String> servletMap;
    private Map<String,String> urlPatternMap;

    private WebContext(){
        servletMap=new HashMap<>();
        urlPatternMap=new HashMap<>();
    }

    public WebContext(List<Enity> enityList, List<Mapping> mappingList) {
        this();
        enityList.stream().forEach(enity->{
            servletMap.put(enity.getName(),enity.getClz());
        });
        mappingList.stream().forEach(mapping -> {
            mapping.getPatterns().stream().forEach(pattern->{
                urlPatternMap.put(pattern,mapping.getName());
            });
        });
    }

    public String getClassPathByUrl(String url) {
        return servletMap.get(urlPatternMap.get(url));
    }
}
