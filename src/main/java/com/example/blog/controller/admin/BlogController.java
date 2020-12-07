package com.example.blog.controller.admin;

import com.example.blog.entity.Blog;
import com.example.blog.entity.Type;
import com.example.blog.service.BlogService;
import com.example.blog.service.TypeService;
import com.example.blog.to.SearchBlogTo;
import com.example.blog.utils.PageUtils;
import com.example.blog.utils.Re;
import com.example.blog.vo.BlogVo;
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
@RequestMapping("admin")
public class BlogController {

    @Autowired
    BlogService blogService;

    @Autowired
    TypeService typeService;

    @RequestMapping("/add-blog.html")
    public String addBlogPage(Integer blogId,Model model){
        List<Type> types = typeService.selectList(0, 10);
        if (blogId != null) {
            Blog blog = blogService.selectById(blogId);
            Type type = typeService.selectById(blog.getTypeId());

            BlogVo blogVo = new BlogVo();
            TypeVo typeVo = new TypeVo();
            BeanUtils.copyProperties(blog,blogVo);
            BeanUtils.copyProperties(type,typeVo);
            blogVo.setType(typeVo);
            model.addAttribute("blogVo", blogVo);

        }
        model.addAttribute("types",types);

        return "admin/blogs-input";
    }

    @PostMapping("/add-blog")
    public String addBlog(Blog blog, RedirectAttributes attributes){

        if(StringUtils.isEmpty(blog.getTitle())){
            attributes.addFlashAttribute("error","请输入标题");
            return "redirect:/admin/add-type.html";
        }

        if(blog.getId()!=null){
            int update = blogService.updateById(blog);
        }
        else {
            int add = blogService.add(blog);
            if(add == 0){
                attributes.addFlashAttribute("error","保存失败,请重试");
                return "redirect:/admin/add-blog.html";
            }
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

        List<Type> types = typeService.selectList(0, 10);

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

        List<BlogVo> blogs =blogService.selectList(page.getStart(), page.getLimit(),search);
        model.addAttribute("blogs",blogs);
        model.addAttribute("page",page);
        model.addAttribute("search",search);
        model.addAttribute("types",types);

        return "admin/blogs";
    }

}
