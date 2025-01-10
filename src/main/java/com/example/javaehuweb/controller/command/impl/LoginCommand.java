package com.example.javaehuweb.controller.command.impl;

import com.example.javaehuweb.controller.command.Command;
import com.example.javaehuweb.controller.command.PageConstants;
import com.example.javaehuweb.exception.CommandException;
import com.example.javaehuweb.exception.ServiceException;
import com.example.javaehuweb.model.User;
import com.example.javaehuweb.service.UserService;
import com.example.javaehuweb.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginCommand implements Command {
    private static final Logger log = LogManager.getLogger(LoginCommand.class);
    private final UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            if (userService.authenticate(login, password)) {
                User user = userService.findUserByLogin(login).orElseThrow(() -> new CommandException("User not found"));
                request.setAttribute("username", user.getName());
                request.setAttribute("role", user.getRole().name());
                return PageConstants.MAIN_PAGE;
            } else {
                request.setAttribute("errorLoginPasswordMessage", "Incorrect login or password");
                return PageConstants.LOGIN_PAGE;
            }
        } catch (ServiceException e) {
            log.error("Error authenticating user", e);
            throw new CommandException("Error authenticating user", e);
        }
    }
}