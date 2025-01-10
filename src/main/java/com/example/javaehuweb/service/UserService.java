package com.example.javaehuweb.service;

import com.example.javaehuweb.model.User;
import com.example.javaehuweb.model.enums.UserRole;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAllUsers();

    boolean authenticate(String login, String password);

    boolean register(String name, String email, String login, String password, UserRole role);

    Optional<User> findUserByLogin(String username);
}
