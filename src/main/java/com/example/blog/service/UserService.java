package com.example.blog.service;


import com.example.blog.entity.User;


public interface UserService {

    User login(String userName, String password);

}
