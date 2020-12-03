package com.example.blog.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("admin")
public class BlogController {

    @RequestMapping("/blogs")
    public String blogs(HttpServletRequest request){

        return "admin/blogs";
    }

}
