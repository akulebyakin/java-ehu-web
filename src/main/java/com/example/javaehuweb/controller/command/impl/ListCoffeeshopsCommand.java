package com.example.javaehuweb.controller.command.impl;

import com.example.javaehuweb.controller.command.Command;
import com.example.javaehuweb.controller.command.PageConstants;
import com.example.javaehuweb.exception.ServiceException;
import com.example.javaehuweb.model.CoffeeShop;
import com.example.javaehuweb.service.CoffeeShopService;
import com.example.javaehuweb.service.impl.CoffeeShopServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Comparator;
import java.util.List;

public class ListCoffeeshopsCommand implements Command {
    private final CoffeeShopService coffeeshopService = new CoffeeShopServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        try {
            List<CoffeeShop> coffeeShops = coffeeshopService.getAllCoffeeShops()
                    .stream()
                    .sorted(Comparator.comparingInt(CoffeeShop::getId))
                    .toList();
            request.setAttribute("coffeeshops", coffeeShops);
            return PageConstants.LIST_COFFEESHOPS_PAGE;
        } catch (ServiceException e) {
            request.setAttribute("errorMessage", "Error retrieving coffee shops: " + e.getMessage());
            return PageConstants.MAIN_PAGE;
        }
    }
}