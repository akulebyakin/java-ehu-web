package com.example.javaehuweb.dao;

import com.example.javaehuweb.DBConnection;
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

public class UserDao {
    private static final Logger log = LogManager.getLogger();

    public List<User> getAllUsers() {
        log.info("Getting all users from the database");
        String query = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(query)) {

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
