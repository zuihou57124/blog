package com.example.blog.dao;

import com.example.blog.entity.Blog;
import com.example.blog.entity.Type;
import com.example.blog.to.SearchBlogTo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Blog record);

    int insertSelective(Blog record);

    Blog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Blog record);

    int updateByPrimaryKey(Blog record);

    List<Blog> selectList(int start, int offset, SearchBlogTo search);

    int count(SearchBlogTo search);

    int addViews(Integer blogId, Integer viewsNum);
}