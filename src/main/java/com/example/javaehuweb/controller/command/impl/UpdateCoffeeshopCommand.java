package com.example.javaehuweb.controller.command.impl;

import com.example.javaehuweb.controller.command.Command;
import com.example.javaehuweb.controller.command.PageConstants;
import com.example.javaehuweb.exception.ServiceException;
import com.example.javaehuweb.model.CoffeeShop;
import com.example.javaehuweb.service.CoffeeShopService;
import com.example.javaehuweb.service.impl.CoffeeShopServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class UpdateCoffeeshopCommand implements Command {
    private final CoffeeShopService coffeeshopService = new CoffeeShopServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String website = request.getParameter("website");
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        int rating = Integer.parseInt(request.getParameter("rating"));

        CoffeeShop coffeeshop = CoffeeShop.builder()
                .id(id)
                .name(name)
                .address(address)
                .phone(phone)
                .email(email)
                .website(website)
                .description(description)
                .image(image)
                .rating(rating)
                .build();

        try {
            boolean isUpdated = coffeeshopService.updateCoffeeShop(coffeeshop);
            if (isUpdated) {
                request.setAttribute("updateMessage", "Coffeeshop updated successfully.");
            } else {
                request.setAttribute("updateMessage", "Failed to update coffeeshop.");
            }
        } catch (ServiceException e) {
            request.setAttribute("updateMessage", "Error updating coffeeshop: " + e.getMessage());
        }

        return PageConstants.UPDATE_COFFEESHOP_PAGE; // Update this to the correct path
    }
}