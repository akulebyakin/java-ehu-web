package com.example.javaehuweb.dao;

import com.example.javaehuweb.exception.DaoException;
import com.example.javaehuweb.model.CoffeeShop;

import java.util.List;
import java.util.Optional;

public interface CoffeeShopDao {
    List<CoffeeShop> findAllCoffeeShops() throws DaoException;

    void saveCoffeeShop(CoffeeShop coffeeshop) throws DaoException;

    boolean updateCoffeeShop(CoffeeShop coffeeshop) throws DaoException;

    Optional<CoffeeShop> findCoffeeShopById(int id) throws DaoException;
}
