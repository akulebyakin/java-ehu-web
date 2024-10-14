package com.example.javaehuweb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static final Logger log = LogManager.getLogger();
    private static final String DB_PROPERTIES_FILE = "db.properties";
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        }

        try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream(DB_PROPERTIES_FILE)) {
            if (input == null) {
                log.error("Unable to find {}", DB_PROPERTIES_FILE);
                return null;
            }

            Properties prop = new Properties();
            prop.load(input);

            connection = DriverManager.getConnection(
                    prop.getProperty("db.url"),
                    prop.getProperty("db.user"),
                    prop.getProperty("db.password")
            );
        } catch (SQLException | IOException e) {
            log.error("Error connecting to the database: {}", e.getMessage());
            throw new RuntimeException(e);
        }

        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            log.error("Error closing the database connection: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
