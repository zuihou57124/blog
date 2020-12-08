package com.example.blog.controller;

import com.example.blog.entity.Type;
import com.example.blog.service.BlogService;
import com.example.blog.service.TypeService;
import com.example.blog.to.SearchBlogTo;
import com.example.blog.utils.PageUtils;
import com.example.blog.vo.BlogVo;
import com.example.blog.vo.TypeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TypeIndexController {

    @Autowired
    TypeService typeService;

    @Autowired
    BlogService blogService;

    /**
     * @param current 当前页
     * @param limit 每页显示记录数
     * @return 类型列表
     */
    @RequestMapping("/types.html")
    public String type(SearchBlogTo search, Integer current, Integer limit, HttpServletRequest request,
                       Model model){

        if(current==null || current<=0){
            current = 1;
        }

        if(limit==null){
            limit = 5;
        }

        if(search.getTypeId()==null){
            search.setTypeId(9);
        }

        List<TypeVo> types = typeService.selectList();

        //初始化分页类
        PageUtils page = new PageUtils();
        page.setNum(blogService.count(search));
        if(page.getNum()==0){

            model.addAttribute("blogs",null);
            model.addAttribute("page",null);
            model.addAttribute("search",null);
            model.addAttribute("types",types);
            return "types";
        }
        page.setLimit(limit);
        page.setTotal(page.getNum()/page.getLimit() + (page.getNum()%page.getLimit()==0?0:1));
        page.setCurrent(current);

        //页码上下限判断
        if(page.getCurrent()>page.getTotal()){
            page.setCurrent(page.getTotal());
        }

        if(page.getCurrent()<=0){
            page.setCurrent(1);
        }
        page.setStart(page.getLimit()*(page.getCurrent()-1));

        List<BlogVo> blogs = blogService.selectList(page.getStart(), page.getLimit(),search);
        //List<BlogVo> recommendBlogs = blogService.recommendBlogs();

        //CountInfoVo countInfoVo = new CountInfoVo();
        //countInfoVo.setNum(blogService.count(null));
        //countInfoVo.setCommentNum();

        model.addAttribute("blogs",blogs);
        model.addAttribute("page",page);
        model.addAttribute("search",search);
        //model.addAttribute("recommendBlogs",recommendBlogs);
        model.addAttribute("types",types);

        return "types";
    }


}
