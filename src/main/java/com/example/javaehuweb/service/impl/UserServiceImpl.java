package com.example.javaehuweb.service.impl;

import com.example.javaehuweb.dao.UserDao;
import com.example.javaehuweb.dao.impl.UserDaoImpl;
import com.example.javaehuweb.exception.DaoException;
import com.example.javaehuweb.exception.ServiceException;
import com.example.javaehuweb.model.User;
import com.example.javaehuweb.model.enums.UserRole;
import com.example.javaehuweb.service.UserService;
import com.example.javaehuweb.service.validator.UserValidator;
import com.example.javaehuweb.service.validator.impl.UserValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    private static final Logger log = LogManager.getLogger();
    private final UserDao userDao;
    private final UserValidator userValidator = new UserValidatorImpl();

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
    public boolean register(String name, String email, String login, String password, UserRole role) {
        try {
            validateNewUser(name, email, login, password);
            String encryptedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            User user = new User(name, email, login, encryptedPassword, role);
            userDao.saveUser(user);
            log.debug("User registered successfully");
            return true;
        } catch (DaoException | IllegalArgumentException e) {
            log.error("Error occurred when registering user", e);
            return false;
        }
    }

    private void validateNewUser(String name, String email, String login, String password) {
        if (name.isBlank()) {
            throw new ServiceException("Name cannot be empty");
        }
        if (!userValidator.isValidEmail(email)) {
            throw new ServiceException("Invalid email format");
        }
        if (!userValidator.isValidLogin(login)) {
            throw new ServiceException("Invalid login format. Login must be at least 3 characters long, contain only letters and digits");
        }
        if (!userValidator.isValidPassword(password)) {
            throw new ServiceException("Invalid password format. Password must be at least 3 characters long, contain only letters and digits");
        }
        if (findUserByLogin(login).isPresent()) {
            throw new ServiceException("User with this login already exists");
        }
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        try {
            return userDao.findUserByLogin(login);
        } catch (DaoException e) {
            log.error("Error finding user by login", e);
            throw new ServiceException("Error finding user by login", e);
        }
    }
}
