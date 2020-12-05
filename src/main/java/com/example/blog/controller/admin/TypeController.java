package com.example.blog.controller.admin;

import com.example.blog.entity.Type;
import com.example.blog.service.TypeService;
import com.example.blog.utils.PageUtils;
import com.example.blog.utils.Re;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TypeController {

    @Autowired
    TypeService typeService;


    /**
     * @param current 当前页
     * @param limit 每页显示记录数
     * @return 类型列表
     */
    @RequestMapping("/types.html")
    public String type(Integer current,Integer limit,HttpServletRequest request,
                        Model model){

        if(current==null || current<=0){
            current = 1;
        }

        if(limit==null){
            limit = 5;
        }

        //初始化分页类
        PageUtils page = new PageUtils();
        page.setNum(typeService.count());
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

        List<Type> types = typeService.selectList(page.getStart(), page.getLimit());
        model.addAttribute("types",types);
        model.addAttribute("page",page);

        return "admin/types";
    }

    @RequestMapping("/add-type.html")
    public String addTypePage(Integer typeId,Model model){

        if(typeId!=null){
            Type type = typeService.selectById(typeId);
            model.addAttribute("type",type);
        }

        return "admin/types-input";
    }

    @PostMapping("/add-type")
    public String addType(Type type, RedirectAttributes attributes){

        if(StringUtils.isEmpty(type.getTypeName())){
            attributes.addFlashAttribute("error","请输入分类名");
            return "redirect:/admin/add-type.html";
        }

        if(type.getId()!=null){
            int update = typeService.updateById(type);
        }
        else {
            int add = typeService.add(type);
            if(add == 0){
                attributes.addFlashAttribute("error","该分类已存在,请勿重复添加");
                return "redirect:/admin/add-type.html";
            }
        }

        return "redirect:/admin/types.html";
    }

    @ResponseBody
    @PostMapping("/del-type")
    public Map<String, Object> delType(Integer typeId){

        int add = typeService.deleteById(typeId);

        return Re.ok();
    }

}
