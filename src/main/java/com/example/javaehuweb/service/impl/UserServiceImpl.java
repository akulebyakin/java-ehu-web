package com.example.javaehuweb.service.impl;

import com.example.javaehuweb.dao.UserDao;
import com.example.javaehuweb.dao.impl.UserDaoImpl;
import com.example.javaehuweb.model.User;
import com.example.javaehuweb.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }
}
