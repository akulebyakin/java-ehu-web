package com.example.javaehuweb.service.validator;

public interface UserValidator {
    boolean isValidLogin(String login);
    boolean isValidPassword(String password);
    boolean isValidEmail(String email);
}