package com.example.javaehuweb.controller.command.impl;

import com.example.javaehuweb.controller.command.Command;
import com.example.javaehuweb.controller.command.PageConstants;
import com.example.javaehuweb.exception.ServiceException;
import com.example.javaehuweb.service.CoffeeShopService;
import com.example.javaehuweb.service.impl.CoffeeShopServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class DeleteCoffeeshopCommand implements Command {
    private final CoffeeShopService coffeeShopService = new CoffeeShopServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String idStr = request.getParameter("id");
        if (idStr != null) {
            try {
                int id = Integer.parseInt(idStr);
                boolean isDeleted = coffeeShopService.deleteCoffeeShopById(id);
                if (isDeleted) {
                    request.setAttribute("successMessage", "Coffee shop deleted successfully.");
                } else {
                    request.setAttribute("errorMessage", "Coffee shop not found.");
                }
            } catch (NumberFormatException | ServiceException e) {
                request.setAttribute("errorMessage", "Error deleting coffee shop: " + e.getMessage());
            }
        } else {
            request.setAttribute("errorMessage", "Invalid coffee shop ID.");
        }
        return PageConstants.LIST_COFFEESHOPS_PAGE;
    }
}