package com.example.javaehuweb.controller.command.impl;

import com.example.javaehuweb.controller.command.Command;
import com.example.javaehuweb.controller.command.PageConstants;
import jakarta.servlet.http.HttpServletRequest;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        request.getSession().invalidate();
        return PageConstants.INDEX_PAGE;
    }
}
