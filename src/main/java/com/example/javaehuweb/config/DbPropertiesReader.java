package com.example.javaehuweb.config;

import com.example.javaehuweb.exception.ConfigException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbPropertiesReader {
    private static final Logger log = LogManager.getLogger();
    private static final String DB_PROPERTIES_FILE = "db.properties";
    private static Properties properties = null;

    public static Properties getProperties() {
        if (properties == null) {
            properties = new Properties();
            try (InputStream input = DbPropertiesReader.class.getClassLoader().getResourceAsStream(DB_PROPERTIES_FILE)) {
                if (input == null) {
                    log.error("Unable to find {}", DB_PROPERTIES_FILE);
                    return null;
                }
                properties.load(input);
            } catch (IOException e) {
                log.error("Error loading properties file: {}", e.getMessage());
                throw new ConfigException("Error loading properties file", e);
            }
        }
        return properties;
    }
}
