package com.example.blog.controller;

import com.example.blog.entity.Blog;
import com.example.blog.entity.Type;
import com.example.blog.exception.NotFoundException;
import com.example.blog.service.BlogService;
import com.example.blog.service.CommentService;
import com.example.blog.service.TypeService;
import com.example.blog.to.SearchBlogTo;
import com.example.blog.utils.PageUtils;
import com.example.blog.utils.Re;
import com.example.blog.vo.BlogVo;
import com.example.blog.vo.CommentVo;
import com.example.blog.vo.CountInfoVo;
import com.example.blog.vo.TypeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class BlogIndexController {

    @Autowired
    BlogService blogService;

    @Autowired
    TypeService typeService;

    @Autowired
    CommentService commentService;

    @RequestMapping("/blog.html")
    public String addBlogPage(Integer blogId,Model model){
        if (blogId != null) {
            Blog blog = blogService.selectById(blogId);

            if(blog==null){ 
                throw new NotFoundException("没有该文章");
            }

            Type type = typeService.selectById(blog.getTypeId());

            BlogVo blogVo = new BlogVo();
            TypeVo typeVo = new TypeVo();
            //查询文章的评论
            List<CommentVo> commentVos = commentService.selectListByBlogId(blogId);
            BeanUtils.copyProperties(blog,blogVo);
            blogVo.setCommentVoList(commentVos);
            BeanUtils.copyProperties(type,typeVo);
            blogVo.setType(typeVo);
            model.addAttribute("blogVo", blogVo);

        }

        else {
            throw new NotFoundException("没有该文章");
        }

        return "blog";
    }

    @ResponseBody
    @RequestMapping("/addViews")
    public Map<String,Object> addViews(Integer blogId,Integer viewsNum){
        int flag = blogService.addViews(blogId, viewsNum);

        return Re.ok();
    }

    /**
     * @param current 当前页
     * @param limit 每页显示记录数
     * @return 类型列表
     */
    @RequestMapping("/index.html")
    public String type(SearchBlogTo search, Integer current, Integer limit, HttpServletRequest request,
                       Model model){

        if(current==null || current<=0){
            current = 1;
        }

        if(limit==null){
            limit = 5;
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
            return "admin/blogs";
        }
        page.setLimit(limit);
        page.setTotal(page.getNum()/page.getLimit() + (page.getNum()%page.getLimit()==0?0:1));
        page.setCurrent(current);

        //页码上下限判断
        if(page.getCurrent()>page.getTotal()){
            page.setCurrent(page.getTotal());
        }

        if(page.getCurrent()<=0){
            page.setCurrent(0);
        }
        page.setStart(page.getLimit()*(page.getCurrent()-1));

        List<BlogVo> blogs = blogService.selectList(page.getStart(), page.getLimit(),search);
        List<BlogVo> recommendBlogs = blogService.recommendBlogs();

        //CountInfoVo countInfoVo = new CountInfoVo();
        //countInfoVo.setNum(blogService.count(null));
        //countInfoVo.setCommentNum();

        model.addAttribute("blogs",blogs);
        model.addAttribute("page",page);
        model.addAttribute("search",search);
        model.addAttribute("recommendBlogs",recommendBlogs);
        model.addAttribute("types",types);

        return "index";
    }

}
