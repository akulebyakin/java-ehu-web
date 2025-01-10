package com.example.javaehuweb.dao;

import com.example.javaehuweb.exception.DaoException;
import com.example.javaehuweb.model.Coffeeshop;

import java.util.List;

public interface CoffeeshopDao {
    List<Coffeeshop> findAllCoffeeshops() throws DaoException;
    void saveCoffeeshop(Coffeeshop coffeeshop) throws DaoException;
}
