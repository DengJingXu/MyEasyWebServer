package com.myserver.core.po;

/**
 * @author: dengjingxu
 * @date: 2020/4/17
 * @description: com.myserver.core.po
 * @version: 1.0
 */
public class Enity {
    private String name;
    private String clz;

    public Enity() {
    }

    public Enity(String name, String clz) {
        this.name = name;
        this.clz = clz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClz() {
        return clz;
    }

    public void setClz(String clz) {
        this.clz = clz;
    }
}
