package com.example.blog.service;

import com.example.blog.entity.Type;

import java.util.List;

public interface TypeService{

    int add(Type type);

    int deleteById(Integer typeId);

    int count();

    /**
     * 分页查询
     */
    List<Type> selectList(int start,int offset);

    Type selectById(Integer id);

    int updateById(Type type);
}
