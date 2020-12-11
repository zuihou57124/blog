package com.example.blog.controller;

import com.example.blog.entity.Comment;
import com.example.blog.service.CommentService;
import com.example.blog.utils.Re;
import com.example.blog.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CommentIndexController {

    @Autowired
    CommentService commentService;


    @ResponseBody
    @PostMapping("/add-comment")
    public Re addComment(Comment comment){

        //一级评论 parentId 的值为0
        if (comment.getParentId() == null) {
            comment.setParentId(0);
        }

        // 如果评论的父评论还存在父评论，就把评论的父评论设置为 评论的父评论的父评论......
        if(comment.getParentId()!=0){
            Comment parent = commentService.findById(comment.getParentId());
            if (parent != null && parent.getParentId()!=0) {
                comment.setParentId(parent.getParentId());
            }
        }

        int add = commentService.insert(comment);

        return Re.ok();
    }

}
