package com.example.javaehuweb.dao.connection;

import com.example.javaehuweb.config.DbPropertiesReader;
import com.example.javaehuweb.exception.ConfigException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

// HikariCP connection pool is not used in the project
public class HikariConnectionPool {
    private static final Logger log = LogManager.getLogger();
    private static final HikariDataSource dataSource;

    static {
        try {
            Properties dbProperties = DbPropertiesReader.getProperties();
            if (dbProperties == null) {
                throw new ConfigException("Database properties are not loaded");
            }

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl(dbProperties.getProperty("db.url"));
            config.setUsername(dbProperties.getProperty("db.username"));
            config.setPassword(dbProperties.getProperty("db.password"));
            config.setDriverClassName(dbProperties.getProperty("db.driverClassName"));
            config.setMaximumPoolSize(Integer.parseInt(dbProperties.getProperty("db.poolSize")));

            // Create the connection pool
            dataSource = new HikariDataSource(config);
            log.debug("HikariCP connection pool initialized");
        } catch (Exception e) {
            log.error("Error initializing HikariCP connection pool", e);
            throw new ConfigException("Error initializing HikariCP connection pool", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new ConfigException("Connection pool is not initialized.");
        }
        return dataSource.getConnection();
    }

    public static void close() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }
}