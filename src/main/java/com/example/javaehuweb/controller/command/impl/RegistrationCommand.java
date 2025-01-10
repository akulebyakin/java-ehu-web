package com.example.javaehuweb.controller.command.impl;

import com.example.javaehuweb.controller.command.Command;
import com.example.javaehuweb.controller.command.PageConstants;
import com.example.javaehuweb.exception.CommandException;
import com.example.javaehuweb.exception.ServiceException;
import com.example.javaehuweb.model.enums.UserRole;
import com.example.javaehuweb.service.UserService;
import com.example.javaehuweb.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RegistrationCommand implements Command {
    private static final Logger log = LogManager.getLogger(RegistrationCommand.class);
    private final UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            if (userService.register(username, email, login, password, UserRole.USER)) {
                request.setAttribute("successfulRegistrationMessage", "User " + username + " registered successfully");
                return PageConstants.LOGIN_PAGE;
            } else {
                request.setAttribute("errorRegistrationMessage", "Registration failed");
                return PageConstants.REGISTRATION_PAGE;
            }
        } catch (ServiceException e) {
            log.error("Error registering user", e);
            request.setAttribute("errorRegistrationMessage", e.getMessage());
            return PageConstants.REGISTRATION_PAGE;
        }
    }
}
