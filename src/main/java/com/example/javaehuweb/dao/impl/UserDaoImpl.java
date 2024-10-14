package com.example.javaehuweb.dao.impl;

import com.example.javaehuweb.DBConnection;
import com.example.javaehuweb.dao.UserDao;
import com.example.javaehuweb.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDaoImpl implements UserDao {
    private static final Logger log = LogManager.getLogger();
    private static final String SQL_GET_ALL_USERS = "SELECT u.id, u.name, u.email FROM users u";

    @Override
    public List<User> findAllUsers() {
        log.info("Getting all users from the database");
        List<User> users = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(SQL_GET_ALL_USERS)) {

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                users.add(user);
            }

        } catch (SQLException e) {
            log.error("Error getting all users: {}", e.getMessage());
            throw new RuntimeException(e);
        }

        return users;
    }
}
