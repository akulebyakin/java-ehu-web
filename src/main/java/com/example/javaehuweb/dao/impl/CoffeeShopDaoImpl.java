package com.example.javaehuweb.dao.impl;

import com.example.javaehuweb.dao.CoffeeShopDao;
import com.example.javaehuweb.dao.connection.ConnectionPool;
import com.example.javaehuweb.exception.DaoException;
import com.example.javaehuweb.model.CoffeeShop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CoffeeShopDaoImpl implements CoffeeShopDao {
    private static final Logger log = LogManager.getLogger();
    private static final String SQL_SELECT_ALL_COFFEESHOPS = "SELECT id, name, address, phone, email, website, description, image, rating FROM coffeeshop";
    private static final String SQL_SAVE_COFFEESHOP = "INSERT INTO coffeeshop (name, address, phone, email, website, description, image, rating) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE_COFFEESHOP = "UPDATE coffeeshop SET name = ?, address = ?, phone = ?, email = ?, website = ?, description = ?, image = ?, rating = ? WHERE id = ?";
    private static final String SQL_SELECT_COFFEESHOP_BY_ID = "SELECT id, name, address, phone, email, website, description, image, rating FROM coffeeshop WHERE id = ?";
    private static final String SQL_DELETE_COFFEESHOP_BY_ID = "DELETE FROM coffeeshop WHERE id = ?"; // New SQL query

    @Override
    public List<CoffeeShop> findAllCoffeeShops() throws DaoException {
        log.info("Getting all coffeeshops from the database");
        List<CoffeeShop> coffeeShops = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_ALL_COFFEESHOPS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CoffeeShop coffeeshop = CoffeeShop.builder()
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
                coffeeShops.add(coffeeshop);
            }
        } catch (SQLException e) {
            log.error("Error getting all coffeeshops: {}", e.getMessage());
            throw new DaoException("Error getting all coffeeshops", e);
        }
        return coffeeShops;
    }

    @Override
    public void saveCoffeeShop(CoffeeShop coffeeshop) throws DaoException {
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

    @Override
    public boolean updateCoffeeShop(CoffeeShop coffeeshop) throws DaoException {
        log.info("Updating coffeeshop with id: {}", coffeeshop.getId());
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_COFFEESHOP)) {
            preparedStatement.setString(1, coffeeshop.getName());
            preparedStatement.setString(2, coffeeshop.getAddress());
            preparedStatement.setString(3, coffeeshop.getPhone());
            preparedStatement.setString(4, coffeeshop.getEmail());
            preparedStatement.setString(5, coffeeshop.getWebsite());
            preparedStatement.setString(6, coffeeshop.getDescription());
            preparedStatement.setString(7, coffeeshop.getImage());
            preparedStatement.setInt(8, coffeeshop.getRating());
            preparedStatement.setInt(9, coffeeshop.getId());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            log.error("Error updating coffeeshop: {}", e.getMessage());
            throw new DaoException("Error updating coffeeshop", e);
        }
    }

    @Override
    public Optional<CoffeeShop> findCoffeeShopById(int id) throws DaoException {
        log.info("Finding coffeeshop with id: {}", id);
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_COFFEESHOP_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                CoffeeShop coffeeShop = CoffeeShop.builder()
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
                return Optional.of(coffeeShop);
            }
        } catch (SQLException e) {
            log.error("Error finding coffeeshop by id: {}", e.getMessage());
            throw new DaoException("Error finding coffeeshop by id", e);
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteCoffeeShopById(int id) throws DaoException { // New method
        log.info("Deleting coffeeshop with id: {}", id);
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_COFFEESHOP_BY_ID)) {
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            log.error("Error deleting coffeeshop: {}", e.getMessage());
            throw new DaoException("Error deleting coffeeshop", e);
        }
    }
}
