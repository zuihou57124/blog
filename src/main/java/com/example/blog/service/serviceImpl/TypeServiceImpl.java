package com.example.blog.service.serviceImpl;

import com.example.blog.dao.TypeDao;
import com.example.blog.entity.Type;
import com.example.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    TypeDao typeDao;

    @Override
    public int add(Type type) {
        return 0;
    }

    @Override
    public int deleteById(Integer typeId) {
        return 0;
    }

    @Override
    public List<Type> selectList(int start, int offset) {
        return null;
    }
}
