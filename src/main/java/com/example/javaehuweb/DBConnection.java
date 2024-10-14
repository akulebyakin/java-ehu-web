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
        log.debug("Getting database connection");
        if (connection != null && !isConnectionClosed()) {
            return connection;
        }

        try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream(DB_PROPERTIES_FILE)) {
            if (input == null) {
                log.error("Unable to find {}", DB_PROPERTIES_FILE);
                return null;
            }

            Properties prop = new Properties();
            prop.load(input);

            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(
                    prop.getProperty("db.url"),
                    prop.getProperty("db.user"),
                    prop.getProperty("db.password")
            );
            log.debug("Database connection established");
        } catch (SQLException | IOException | ClassNotFoundException e) {
            log.error("Error: {}", e.getMessage());
            throw new RuntimeException(e);
        }

        return connection;
    }

    private static boolean isConnectionClosed() {
        if (connection == null) {
            return true;
        }
        try {
            return connection.isClosed();
        } catch (SQLException e) {
            log.error("Error checking if the connection is closed: {}", e.getMessage());
            throw new RuntimeException(e);
        }
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
