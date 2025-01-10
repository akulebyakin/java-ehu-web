package com.example.javaehuweb.dao;

import com.example.javaehuweb.exception.DaoException;
import com.example.javaehuweb.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> findAllUsers() throws DaoException;

    String findUserPassword(String login) throws DaoException;

    Optional<User> findUserByLogin(String login) throws DaoException;

    void saveUser(User user) throws DaoException;
}
