package com.example.blog.controller.admin;

import com.example.blog.service.TypeService;
import com.example.blog.service.serviceImpl.TypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("admin")
public class TypeController {

    @Autowired
    TypeService typeService;

    @RequestMapping("/types")
    public String type(HttpServletRequest request){

        return "admin/types";
    }

    @RequestMapping("/add-type")
    public String addType(){

        return "admin/types-input";
    }

}
