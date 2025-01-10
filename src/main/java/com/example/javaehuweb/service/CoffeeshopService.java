package com.example.javaehuweb.service;

import com.example.javaehuweb.exception.ServiceException;
import com.example.javaehuweb.model.Coffeeshop;

import java.util.List;

public interface CoffeeshopService {
    List<Coffeeshop> getAllCoffeeshops() throws ServiceException;
    void addCoffeeshop(Coffeeshop coffeeshop) throws ServiceException;
}
