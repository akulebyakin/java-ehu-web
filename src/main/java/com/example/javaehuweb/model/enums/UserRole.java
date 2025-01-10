package com.example.javaehuweb.model.enums;

public enum UserRole {
    ADMIN,
    USER,
    UNDEFINED;

    public static UserRole getRole(String role) {
        try {
            return UserRole.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNDEFINED;
        }
    }
}
