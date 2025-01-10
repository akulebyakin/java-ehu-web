package com.example.javaehuweb.controller.command;

import com.example.javaehuweb.exception.CommandException;
import com.example.javaehuweb.exception.ServiceException;
import com.example.javaehuweb.service.UserService;
import com.example.javaehuweb.service.impl.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginCommand implements Command {
    private static final Logger log = LogManager.getLogger(LoginCommand.class);
    private static final String MAIN_PAGE = "/jsp/main.jsp";
    private static final String LOGIN_PAGE = "/jsp/login.jsp";
    private final UserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        try {
            if (userService.authenticate(login, password)) {
                request.setAttribute("user", login);
                return MAIN_PAGE;
            } else {
                request.setAttribute("errorLoginPasswordMessage", "Incorrect login or password");
                return LOGIN_PAGE;
            }
        } catch (ServiceException e) {
            log.error("Error authenticating user", e);
            throw new CommandException("Error authenticating user", e);
        }
    }
}