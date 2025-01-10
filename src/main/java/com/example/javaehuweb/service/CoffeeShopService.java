package com.example.javaehuweb.service;

import com.example.javaehuweb.exception.ServiceException;
import com.example.javaehuweb.model.CoffeeShop;

import java.util.List;
import java.util.Optional;

public interface CoffeeShopService {
    List<CoffeeShop> getAllCoffeeShops() throws ServiceException;

    void addCoffeeShop(CoffeeShop coffeeShop) throws ServiceException;

    boolean updateCoffeeShop(CoffeeShop coffeeShop) throws ServiceException;

    Optional<CoffeeShop> getCoffeeShopById(int id) throws ServiceException;
}
