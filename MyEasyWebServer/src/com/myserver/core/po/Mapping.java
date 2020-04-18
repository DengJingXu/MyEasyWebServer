package com.myserver.core.po;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author: dengjingxu
 * @date: 2020/4/17
 * @description: com.myserver.core.po
 * @version: 1.0
 */
public class Mapping {
    private String name;
    private List<String> patterns;

    public Mapping() {
        patterns=new LinkedList<>();
    }

    public Mapping(String name, List<String> patterns) {
        this.name = name;
        this.patterns = patterns;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPatterns() {
        return patterns;
    }

    public void setPatterns(List<String> patterns) {
        this.patterns = patterns;
    }
}
