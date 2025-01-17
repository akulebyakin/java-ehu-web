package com.example.javaehuweb.controller.command.impl;


import com.example.javaehuweb.controller.command.Command;
import com.example.javaehuweb.controller.command.PageConstants;
import com.example.javaehuweb.exception.ServiceException;
import com.example.javaehuweb.model.CoffeeShop;
import com.example.javaehuweb.service.CoffeeShopService;
import com.example.javaehuweb.service.impl.CoffeeShopServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

public class RegisterCoffeeshopCommand implements Command {
    private final CoffeeShopService coffeeshopService = new CoffeeShopServiceImpl();

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String website = request.getParameter("website");
        String description = request.getParameter("description");
        String image = request.getParameter("image");
        int rating = Integer.parseInt(request.getParameter("rating"));

        CoffeeShop coffeeshop = CoffeeShop.builder()
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
            coffeeshopService.addCoffeeShop(coffeeshop);
            request.setAttribute("registrationMessage", "Coffeeshop " + name + " registered successfully");
            return PageConstants.REGISTER_NEW_COFFEESHOP_PAGE;
        } catch (ServiceException e) {
            request.setAttribute("registrationMessage", "Error registering coffee shop: " + e.getMessage());
            return PageConstants.REGISTER_NEW_COFFEESHOP_PAGE;
        }
    }
}
