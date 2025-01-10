package com.example.javaehuweb.dao.connection;

import com.example.javaehuweb.config.DbPropertiesReader;
import com.example.javaehuweb.exception.ConfigException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class ConnectionPool {
    private static final Logger log = LogManager.getLogger();
    private static final int DEFAULT_NUMBER_OF_CONNECTIONS = 8;
    private static final CountDownLatch latch = new CountDownLatch(1);
    private static final AtomicBoolean isInitialized = new AtomicBoolean(false);
    private static ConnectionPool instance;
    private final BlockingQueue<Connection> freeConnections;
    private final BlockingQueue<Connection> usedConnections;

    static {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            log.debug("Driver registered");
        } catch (SQLException e) {
            log.error("Failed to register a driver", e);
            throw new RuntimeException(e);
        }
    }

    private ConnectionPool() {
        log.debug("Connection pool initialization");
        Properties dbProperties = DbPropertiesReader.getProperties();
        if (dbProperties == null) {
            throw new ConfigException("Database properties are not loaded");
        }
        int numberOfConnections = Integer.parseInt(dbProperties.getProperty("db.poolSize",
                String.valueOf(DEFAULT_NUMBER_OF_CONNECTIONS)));

        freeConnections = new LinkedBlockingQueue<>(numberOfConnections);
        usedConnections = new LinkedBlockingQueue<>(numberOfConnections);

        for (int i = 0; i < numberOfConnections; i++) {
            Connection connection;
            try {
                connection = DriverManager.getConnection(
                        dbProperties.getProperty("db.url"),
                        dbProperties.getProperty("db.username"),
                        dbProperties.getProperty("db.password"));
            } catch (SQLException e) {
                log.error("Failed to create a connection", e);
                throw new ExceptionInInitializerError(e);
            }
            freeConnections.add(connection);
        }
        log.debug("Connection pool initialized");
    }

    public static ConnectionPool getInstance() {
        // initialize the instance only once
        if (isInitialized.compareAndSet(false, true)) {
            instance = new ConnectionPool();
            latch.countDown();
        } else {
            try {
                // wait for the instance to be initialized
                latch.await();
            } catch (InterruptedException e) {
                log.error("Failed to await latch", e);
                Thread.currentThread().interrupt();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        log.debug("Getting a connection from Connection Pool");
        Connection connection = null;
        try {
            connection = freeConnections.take();
            usedConnections.add(connection);
        } catch (InterruptedException e) {
            log.error("Failed to get a connection", e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        try {
            usedConnections.remove(connection);
            freeConnections.put(connection);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeAllConnections() {
        closeConnections(freeConnections);
        closeConnections(usedConnections);
    }

    private void closeConnections(BlockingQueue<Connection> connections) {
        for (Connection connection : connections) {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error("Failed to close a connection", e);
                }
            }
        }
    }

    public void deregisterDrivers() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
