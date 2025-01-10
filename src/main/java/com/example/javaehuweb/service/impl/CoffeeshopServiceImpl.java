package com.example.javaehuweb.service.impl;


import com.example.javaehuweb.dao.CoffeeshopDao;
import com.example.javaehuweb.dao.impl.CoffeeshopDaoImpl;
import com.example.javaehuweb.exception.DaoException;
import com.example.javaehuweb.exception.ServiceException;
import com.example.javaehuweb.model.Coffeeshop;
import com.example.javaehuweb.service.CoffeeshopService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CoffeeshopServiceImpl implements CoffeeshopService {
    private static final Logger log = LogManager.getLogger();
    private final CoffeeshopDao coffeeshopDao;

    public CoffeeshopServiceImpl() {
        this.coffeeshopDao = new CoffeeshopDaoImpl();
    }

    public List<Coffeeshop> getAllCoffeeshops() throws ServiceException {
        try {
            return coffeeshopDao.findAllCoffeeshops();
        } catch (DaoException e) {
            log.error("Error getting all coffeeshops: {}", e.getMessage());
            throw new ServiceException("Error getting all coffeeshops", e);
        }
    }

    public void addCoffeeshop(Coffeeshop coffeeshop) throws ServiceException {
        try {
            coffeeshopDao.saveCoffeeshop(coffeeshop);
        } catch (DaoException e) {
            log.error("Error saving coffeeshop: {}", e.getMessage());
            throw new ServiceException("Error saving coffeeshop", e);
        }
    }
}
