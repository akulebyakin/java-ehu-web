package com.example.javaehuweb.service.impl;


import com.example.javaehuweb.dao.CoffeeShopDao;
import com.example.javaehuweb.dao.impl.CoffeeShopDaoImpl;
import com.example.javaehuweb.exception.DaoException;
import com.example.javaehuweb.exception.ServiceException;
import com.example.javaehuweb.model.CoffeeShop;
import com.example.javaehuweb.service.CoffeeShopService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class CoffeeShopServiceImpl implements CoffeeShopService {
    private static final Logger log = LogManager.getLogger();
    private final CoffeeShopDao coffeeShopDao;

    public CoffeeShopServiceImpl() {
        this.coffeeShopDao = new CoffeeShopDaoImpl();
    }

    public List<CoffeeShop> getAllCoffeeShops() throws ServiceException {
        try {
            return coffeeShopDao.findAllCoffeeShops();
        } catch (DaoException e) {
            log.error("Error getting all coffeeshops: {}", e.getMessage());
            throw new ServiceException("Error getting all coffeeshops", e);
        }
    }

    public void addCoffeeShop(CoffeeShop coffeeShop) throws ServiceException {
        try {
            coffeeShopDao.saveCoffeeShop(coffeeShop);
        } catch (DaoException e) {
            log.error("Error saving coffeeshop: {}", e.getMessage());
            throw new ServiceException("Error saving coffeeshop", e);
        }
    }

    @Override
    public boolean updateCoffeeShop(CoffeeShop coffeeShop) throws ServiceException {
        try {
            return coffeeShopDao.updateCoffeeShop(coffeeShop);
        } catch (DaoException e) {
            log.error("Error updating coffeeshop: {}", e.getMessage());
            throw new ServiceException("Error updating coffeeshop", e);
        }
    }

    @Override
    public Optional<CoffeeShop> getCoffeeShopById(int id) throws ServiceException {
        try {
            return coffeeShopDao.findCoffeeShopById(id);
        } catch (DaoException e) {
            log.error("Error getting coffeeshop by id: {}", e.getMessage());
            throw new ServiceException("Error getting coffeeshop by id", e);
        }
    }

    @Override
    public boolean deleteCoffeeShopById(int id) throws ServiceException { // New method
        try {
            return coffeeShopDao.deleteCoffeeShopById(id);
        } catch (DaoException e) {
            log.error("Error deleting coffeeshop: {}", e.getMessage());
            throw new ServiceException("Error deleting coffeeshop", e);
        }
    }
}
