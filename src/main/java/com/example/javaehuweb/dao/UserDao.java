package com.example.javaehuweb.dao;

import com.example.javaehuweb.model.User;

import java.util.List;

public interface UserDao {
    List<User> findAllUsers();
}
