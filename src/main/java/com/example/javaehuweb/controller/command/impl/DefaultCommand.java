package com.example.javaehuweb.controller.command.impl;

import com.example.javaehuweb.controller.command.Command;
import jakarta.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        return "index.jsp";
    }
}
