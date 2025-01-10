package com.example.javaehuweb.model;

import com.example.javaehuweb.model.enums.UserRole;

import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String email;
    private String login;
    private String encryptedPassword;
    private UserRole role;

    public User() {
        this.role = UserRole.UNDEFINED;
    }

    public User(String name, String email, String login, String encryptedPassword) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.encryptedPassword = encryptedPassword;
        this.role = UserRole.UNDEFINED;
    }

    public User(int id, String name, String email, String login, String encryptedPassword) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.login = login;
        this.encryptedPassword = encryptedPassword;
        this.role = UserRole.UNDEFINED;
    }

    public User(String name, String email, String login, String encryptedPassword, UserRole role) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.encryptedPassword = encryptedPassword;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                Objects.equals(name, user.name) &&
                Objects.equals(email, user.email) &&
                Objects.equals(login, user.login) &&
                Objects.equals(encryptedPassword, user.encryptedPassword) &&
                role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, login, encryptedPassword, role);
    }
}