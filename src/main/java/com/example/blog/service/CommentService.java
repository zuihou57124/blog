package com.example.blog.service;

import com.example.blog.entity.Comment;
import com.example.blog.vo.CommentVo;

import java.util.List;

public interface CommentService {

    int insert(Comment comment);

    List<CommentVo> selectListByParentId(CommentVo comment);

    List<CommentVo> selectListByBlogId(Integer id);

    Comment findById(Integer commentId);

}
