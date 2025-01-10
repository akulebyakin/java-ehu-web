package com.example.javaehuweb.controller.command;

import com.example.javaehuweb.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

public interface Command {

    String execute(HttpServletRequest request) throws CommandException;
}
