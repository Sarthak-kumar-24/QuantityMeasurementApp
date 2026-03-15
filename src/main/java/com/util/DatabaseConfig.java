package com.util;

import java.io.InputStream;
import java.util.Properties;

/*
 * =========================================================
 * DatabaseConfig
 * =========================================================
 *
 * UC16 – Configuration Utility
 *
 * Purpose:
 * Loads database configuration from application.properties
 *
 * Used for:
 * - Database URL
 * - Username
 * - Password
 * - Repository type (cache or database)
 */

public class DatabaseConfig {

    private static final Properties properties = new Properties();

    static {

        try (InputStream input =
                     DatabaseConfig.class
                             .getClassLoader()
                             .getResourceAsStream("application.properties")) {

            if (input == null) {
                throw new RuntimeException(
                        "application.properties not found in resources folder");
            }

            properties.load(input);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to load database configuration", e);
        }
    }

    /*
     * =========================================================
     * Configuration Getters
     * =========================================================
     */

    public static String getRepositoryType() {
        return properties.getProperty("repository.type", "cache");
    }

    public static String getDbUrl() {
        return properties.getProperty("db.url");
    }

    public static String getDbUsername() {
        return properties.getProperty("db.username");
    }

    public static String getDbPassword() {
        return properties.getProperty("db.password");
    }

    public static String getDbDriver() {
        return properties.getProperty("db.driver");
    }
}