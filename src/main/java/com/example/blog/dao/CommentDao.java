package com.example.blog.dao;

import com.example.blog.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Comment record);

    int insertSelective(Comment record);

    Comment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Comment record);

    int updateByPrimaryKey(Comment record);

    //查询一级评论
    List<Comment> selectByBlogId(Integer id);

    //查询子评论
    List<Comment> selectByParentId(Integer id);
}