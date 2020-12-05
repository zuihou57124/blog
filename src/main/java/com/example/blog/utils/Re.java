package com.example.blog.utils;

import lombok.Data;

import java.util.HashMap;

@Data
public class Re extends HashMap<String,Object> {

    private Integer code;

    private String msg;

    public Re(){
        put("code", 0);
        put("msg", "success");
    }

    public Re put(String name, Object o){
        super.put(name,o);
        return this;
    }

    public static Re ok(String info){
        Re r = new Re();
        r.code = 0;
        r.msg = info;
        return r;
    }

    public static Re ok(){
        Re r = new Re();
        r.code = 0;
        return r;
    }

    public static Re error(String info){
        Re r = new Re();
        r.code = 1;
        r.msg = info;
        return r;
    }

    public static Re error(){
        Re r = new Re();
        r.code = 1;
        return r;
    }


}
