package com.example.javaehuweb.dao.impl;

import com.example.javaehuweb.dao.CoffeeshopDao;
import com.example.javaehuweb.dao.connection.ConnectionPool;
import com.example.javaehuweb.exception.DaoException;
import com.example.javaehuweb.model.Coffeeshop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoffeeshopDaoImpl implements CoffeeshopDao {
    private static final Logger log = LogManager.getLogger();
    private static final String SQL_SELECT_ALL_COFFEESHOPS = "SELECT id, name, address, phone, email, website, description, image, rating FROM coffeeshop";
    private static final String SQL_SAVE_COFFEESHOP = "INSERT INTO coffeeshop (name, address, phone, email, website, description, image, rating) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    @Override
    public List<Coffeeshop> findAllCoffeeshops() throws DaoException {
        log.info("Getting all coffeeshops from the database");
        List<Coffeeshop> coffeeshops = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_COFFEESHOPS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Coffeeshop coffeeshop = Coffeeshop.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .address(resultSet.getString("address"))
                        .phone(resultSet.getString("phone"))
                        .email(resultSet.getString("email"))
                        .website(resultSet.getString("website"))
                        .description(resultSet.getString("description"))
                        .image(resultSet.getString("image"))
                        .rating(resultSet.getInt("rating"))
                        .build();
                coffeeshops.add(coffeeshop);
            }
        } catch (SQLException e) {
            log.error("Error getting all coffeeshops: {}", e.getMessage());
            throw new DaoException("Error getting all coffeeshops", e);
        }
        return coffeeshops;
    }

    @Override
    public void saveCoffeeshop(Coffeeshop coffeeshop) throws DaoException {
        log.info("Saving coffeeshop with name: {}", coffeeshop.getName());
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_COFFEESHOP)) {
            preparedStatement.setString(1, coffeeshop.getName());
            preparedStatement.setString(2, coffeeshop.getAddress());
            preparedStatement.setString(3, coffeeshop.getPhone());
            preparedStatement.setString(4, coffeeshop.getEmail());
            preparedStatement.setString(5, coffeeshop.getWebsite());
            preparedStatement.setString(6, coffeeshop.getDescription());
            preparedStatement.setString(7, coffeeshop.getImage());
            preparedStatement.setInt(8, coffeeshop.getRating()); // Changed to int
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            log.error("Error saving coffeeshop: {}", e.getMessage());
            throw new DaoException("Error saving coffeeshop", e);
        }
    }
}
