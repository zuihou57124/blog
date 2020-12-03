package com.example.blog.service.serviceImpl;

import com.example.blog.dao.UserDao;
import com.example.blog.entity.User;
import com.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public User login(String userName, String password) {

        return userDao.login(userName, password);
    }
}
