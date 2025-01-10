package com.example.javaehuweb.controller.command.impl;

import com.example.javaehuweb.controller.command.Command;
import com.example.javaehuweb.controller.command.PageConstants;
import com.example.javaehuweb.exception.ServiceException;
import com.example.javaehuweb.model.Coffeeshop;
import com.example.javaehuweb.service.CoffeeshopService;
import com.example.javaehuweb.service.impl.CoffeeshopServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public class ListCoffeeshopsCommand implements Command {
    private final CoffeeshopService coffeeshopService = new CoffeeshopServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        try {
            List<Coffeeshop> coffeeshops = coffeeshopService.getAllCoffeeshops();
            request.setAttribute("coffeeshops", coffeeshops);
            return PageConstants.LIST_COFFEESHOPS_PAGE;
        } catch (ServiceException e) {
            request.setAttribute("errorMessage", "Error retrieving coffee shops: " + e.getMessage());
            return PageConstants.MAIN_PAGE;
        }
    }
}