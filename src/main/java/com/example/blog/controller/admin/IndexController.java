package com.example.blog.controller.admin;

import com.example.blog.entity.User;
import com.example.blog.exception.NotFoundException;
import com.example.blog.service.UserService;
import com.example.blog.vo.UserVo;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")
public class IndexController {

    @Autowired
    UserService userService;

    @Autowired
    StringEncryptor encryptor;

    /**
     * 首页
     */
    @RequestMapping("/")
    public String index(HttpServletRequest request){

        return "admin/index";
}

    @RequestMapping("/toLogin")
    public String toLogin(){

        return "admin/login";
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public String login(UserVo userVo, HttpServletRequest request,
                        RedirectAttributes redirectAttributes){

        //首先通过用户名查出用户，然后解析用户的密码，与前端值对比
        User user = userService.login(userVo.getUsername(), encryptor.encrypt(userVo.getPassword()));
        if(user!=null && encryptor.decrypt(user.getPassword()).equals(userVo.getPassword())){

            request.getSession().setAttribute("user",userVo);

            return "redirect:/admin/";
        }
        //密码或用户名错误
        redirectAttributes.addFlashAttribute("loginfail",1);

        return "redirect:/admin/toLogin";
    }

    /**
     * 注销
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){

        request.getSession().removeAttribute("user");

        return "redirect:/admin/toLogin";
    }

}
