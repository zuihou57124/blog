package com.example.blog.service;

import com.example.blog.entity.Blog;
import com.example.blog.entity.Type;
import com.example.blog.to.SearchBlogTo;
import com.example.blog.vo.BlogVo;

import java.util.List;

public interface BlogService {

    int add(Blog blog);

    int deleteById(Integer blogId);

    int count(SearchBlogTo search);

    /**
     * 分页查询
     */
    List<BlogVo> selectList(int start, int offset, SearchBlogTo search);

    Blog selectById(Integer id);

    int updateById(Blog blog);

}
