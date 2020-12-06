package com.example.blog.controller.admin;

import com.example.blog.entity.Blog;
import com.example.blog.entity.Type;
import com.example.blog.service.BlogService;
import com.example.blog.to.SearchBlogTo;
import com.example.blog.utils.PageUtils;
import com.example.blog.utils.Re;
import com.example.blog.vo.BlogVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class BlogController {

    @Autowired
    BlogService blogService;

    @RequestMapping("/add-blog.html")
    public String addBlog(Integer blogId,Model model){
        if (blogId != null) {
            Blog blog = blogService.selectById(blogId);
            model.addAttribute("blog", blog);
        }
        return "admin/blogs-input";
    }

    @PostMapping("/add-blog")
    public String addBlog(Blog blog){
        if(blog.getId()!=null){
            int update = blogService.updateById(blog);
        }
        else {
            int add = blogService.add(blog);
        }

        return "redirect:/admin/blogs.html";
    }

    @ResponseBody
    @PostMapping("/del-blog")
    public Map<String, Object> delBlog(Integer blogId){

        int add = blogService.deleteById(blogId);

        return Re.ok();
    }

    /**
     * @param current 当前页
     * @param limit 每页显示记录数
     * @return 类型列表
     */
    @RequestMapping("/blogs.html")
    public String type(SearchBlogTo search, Integer current, Integer limit, HttpServletRequest request,
                       Model model){

        if(current==null || current<=0){
            current = 1;
        }

        if(limit==null){
            limit = 5;
        }

        //初始化分页类
        PageUtils page = new PageUtils();
        page.setNum(blogService.count(search));
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

        List<BlogVo> blogs =blogService.selectList(page.getStart(), page.getLimit(),search);
        model.addAttribute("blogs",blogs);
        model.addAttribute("page",page);
        model.addAttribute("search",search);

        return "admin/blogs";
    }

}
