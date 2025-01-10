package com.example.javaehuweb.service.validator.impl;

import com.example.javaehuweb.service.validator.UserValidator;

public class UserValidatorImpl implements UserValidator {
    // Login regex: 3 or more characters, only letters and digits
    private static final String LOGIN_REGEX = "^[a-zA-Z0-9]{3,}$";
    // Password regex: 3 or more characters, only letters and digits
    private static final String PASSWORD_REGEX = "^[a-zA-Z0-9]{3,}$";
    // Email regex: any symbols, @, any symbols, dot, any symbols
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$";

    @Override
    public boolean isValidLogin(String login) {
        return login != null && login.matches(LOGIN_REGEX);
    }

    @Override
    public boolean isValidPassword(String password) {
        return password != null && password.matches(PASSWORD_REGEX);
    }

    @Override
    public boolean isValidEmail(String email) {
        return email != null && email.matches(EMAIL_REGEX);
    }
}