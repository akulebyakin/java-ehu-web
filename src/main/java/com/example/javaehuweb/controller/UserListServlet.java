package com.example.javaehuweb.controller;

import com.example.javaehuweb.model.User;
import com.example.javaehuweb.service.UserService;
import com.example.javaehuweb.service.impl.UserServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "userListServlet", value = "/users")
public class UserListServlet extends HttpServlet {
    private static final Logger log = LogManager.getLogger();
    private final UserService userService = new UserServiceImpl();

    @Override
    public void init() {
        log.debug("Servlet {} has started.", this.getServletName());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("GET request received");
        List<User> users = userService.findAllUsers();
        StringBuilder userListHtml = new StringBuilder();

        userListHtml.append("<ul>");
        if (users != null && !users.isEmpty()) {
            for (User user : users) {
                userListHtml.append("<li>ID: ")
                        .append(user.getId())
                        .append(", Name: ")
                        .append(user.getName())
                        .append(", Email: ")
                        .append(user.getEmail())
                        .append("</li>");
            }
        } else {
            userListHtml.append("<li>No users found.</li>");
        }
        userListHtml.append("</ul>");

        request.setAttribute("users", userListHtml.toString());
        request.getRequestDispatcher("/jsp/userList.jsp").forward(request, response);
        log.debug("GET request processed");
    }

    @Override
    public void destroy() {
    }
}
