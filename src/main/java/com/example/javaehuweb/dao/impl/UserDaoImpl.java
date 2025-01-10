package com.example.javaehuweb.dao.impl;

import com.example.javaehuweb.dao.UserDao;
import com.example.javaehuweb.dao.connection.ConnectionPool;
import com.example.javaehuweb.exception.DaoException;
import com.example.javaehuweb.model.User;
import com.example.javaehuweb.model.enums.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private static final Logger log = LogManager.getLogger();
    private static final String SQL_SELECT_ALL_USERS = "SELECT id, name, email, role FROM users";
    private static final String SQL_SELECT_PASSWORD = "SELECT password FROM users WHERE login = ?";
    private static final String SQL_SELECT_USER_BY_LOGIN = "SELECT id, name, email, login, role FROM users WHERE login = ?";
    private static final String SQL_SAVE_USER = "INSERT INTO users (name, email, login, password, role) VALUES (?, ?, ?, ?, ?)";

    @Override
    public List<User> findAllUsers() throws DaoException {
        log.info("Getting all users from the database");
        List<User> users = new ArrayList<>();
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setRole(UserRole.getRole(resultSet.getString("role")));
                users.add(user);
            }
        } catch (SQLException e) {
            log.error("Error getting all users: {}", e.getMessage());
            throw new DaoException("Error getting all users", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return users;
    }

    @Override
    public String findUserPassword(String login) throws DaoException {
        log.info("Authenticating user with login: {}", login);
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_PASSWORD)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("password");
            }
        } catch (SQLException e) {
            log.error("Error authenticating user: {}", e.getMessage());
            throw new DaoException("Error authenticating user", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return "";
    }

    @Override
    public Optional<User> findUserByLogin(String login) throws DaoException {
        log.info("Finding user with login: {}", login);
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_USER_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setEmail(resultSet.getString("email"));
                user.setLogin(resultSet.getString("login"));
                user.setRole(UserRole.getRole(resultSet.getString("role")));
                return Optional.of(user);
            }
        } catch (SQLException e) {
            log.error("Error finding user: {}", e.getMessage());
            throw new DaoException("Error finding user", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
        return Optional.empty();
    }

    @Override
    public void saveUser(User user) throws DaoException {
        log.info("Saving user with login: {}", user.getLogin());
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_USER)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getEncryptedPassword());
            preparedStatement.setString(5, user.getRole().name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error saving user: {}", e.getMessage());
            throw new DaoException("Error saving user", e);
        } finally {
            if (connection != null) {
                ConnectionPool.getInstance().releaseConnection(connection);
            }
        }
    }
}
