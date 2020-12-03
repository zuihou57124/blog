package com.example.blog.dao;

import com.example.blog.entity.Type;
import org.apache.ibatis.annotations.Mapper;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Mapper
public interface TypeDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Type record);

    int insertSelective(Type record);

    Type selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Type record);

    int updateByPrimaryKey(Type record);

    List<Type> selectList (int start, int offset);

}