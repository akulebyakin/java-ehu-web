package com.example.javaehuweb.service.impl;

import com.example.javaehuweb.dao.UserDao;
import com.example.javaehuweb.dao.impl.UserDaoImpl;
import com.example.javaehuweb.exception.DaoException;
import com.example.javaehuweb.exception.ServiceException;
import com.example.javaehuweb.model.User;
import com.example.javaehuweb.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger log = LogManager.getLogger();
    private final UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoImpl();
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        try {
            return userDao.findAllUsers();
        } catch (DaoException e) {
            log.error("Error getting all users from DB", e);
            throw new ServiceException("Error getting all users", e);
        }
    }

    @Override
    public boolean authenticate(String login, String password) {
        String storedEncryptedPassword;
        try {
            storedEncryptedPassword = userDao.findUserPassword(login);
            return BCrypt.checkpw(password, storedEncryptedPassword);
        } catch (DaoException | RuntimeException e) {
            log.error("Error getting user password from DB", e);
            return false;
        }
    }

    @Override
    public boolean register(String name, String email, String login, String password) {
        try {
            String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            User user = new User(name, email, login, encryptedPassword);
            userDao.saveUser(user);
            return true;
        } catch (DaoException | IllegalArgumentException e) {
            log.error("Error saving user to DB", e);
            throw new ServiceException("Error saving user", e);
        }
    }

    @Override
    public Optional<User> findByUsername(String login) {
        try {
            return userDao.findUserByLogin(login);
        } catch (DaoException e) {
            log.error("Error finding user by login", e);
            throw new ServiceException("Error finding user by login", e);
        }
    }
}
