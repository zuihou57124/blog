package com.example.handler;

import com.example.blog.exception.NotFoundException;
import org.slf4j.Logger;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    private Logger logger;

    //统一拦截 异常 返回 "error.html" 页面
    @ExceptionHandler(RuntimeException.class)
    public ModelAndView exceptionHandler(HttpServletRequest request,Exception e) throws Exception {
        logger.error("请求url : {} , 异常: {} ",request.getRequestURL(),e);

        if(AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class)!=null){
            throw e;
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("url",request.getRequestURL());
        modelAndView.addObject("exception",e);
        modelAndView.setViewName("/error/error");

        return modelAndView;
    }

}
