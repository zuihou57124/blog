package com.example.blog.config;

import com.example.blog.intceptor.LoginIntecptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class MyConfig implements WebMvcConfigurer {

    @Autowired
    LoginIntecptor loginIntecptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginIntecptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/admin/login.html","/","/admin/login","/login")
                .excludePathPatterns("/index.html","/types.html","/blog.html")
                .excludePathPatterns("/addViews")
                //放行静态资源
                .excludePathPatterns("/static/**");

    }


}
