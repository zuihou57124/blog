package com.example.blog.controller;

import com.example.blog.exception.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(){

        String blog = "sss";
        if(blog==null){
            throw new NotFoundException("文章不存在");
        }

        return "index";
    }

}
