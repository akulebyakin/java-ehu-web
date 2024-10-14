package com.example.javaehuweb.servlet;

import com.example.javaehuweb.dao.UserDao;
import com.example.javaehuweb.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "userListServlet", value = "/users")
public class UserListServlet extends HttpServlet {

    private final UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userDao.getAllUsers();
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
        request.getRequestDispatcher("userList.jsp").forward(request, response);
    }

    public void destroy() {
    }
}
