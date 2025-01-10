package com.example.javaehuweb.model;

import java.util.Objects;

public class User {
    private int id;
    private String name;
    private String email;
    private String login;
    private String encryptedPassword;

    public User() {
    }

    public User(String name, String email, String login, String encryptedPassword) {
        this.name = name;
        this.email = email;
        this.login = login;
        this.encryptedPassword = encryptedPassword;
    }

    public User(int id, String name, String email, String login, String encryptedPassword) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.login = login;
        this.encryptedPassword = encryptedPassword;
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

    @Override
    public String toString() {
        return String.format("User{id=%d, name='%s', email='%s', login='%s', password='%s'}", id, name, email, login, encryptedPassword);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(login, user.login) && Objects.equals(encryptedPassword, user.encryptedPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, login, encryptedPassword);
    }
}