package com.util;


import java.sql.Connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/*
 * =========================================================
 * ConnectionPool
 * =========================================================
 *
 * UC16 – Database Connection Pool
 *
 * Purpose:
 * Provides efficient database connection management
 * using HikariCP.
 *
 * Benefits:
 * - Faster database access
 * - Reuses connections
 * - Reduces connection overhead
 * 
 * without pool : Request -> create connection -> query -> close connection
 * with pool : Request -> reuse existing connection -> query -> return to pool
 */

public class ConnectionPool {

	/*
	 Zaxxer is the company that created HikariCP.

     HikariCP is simply a very fast connection pool library.

     Used by:

       Spring Boot
       Micronaut
	 */
    private static HikariDataSource dataSource;

    static {

        try {

            HikariConfig config = new HikariConfig();

            config.setJdbcUrl(DatabaseConfig.getDbUrl());
            config.setUsername(DatabaseConfig.getDbUsername());
            config.setPassword(DatabaseConfig.getDbPassword());

            config.setDriverClassName(DatabaseConfig.getDbDriver());

            config.setMaximumPoolSize(10);
            config.setMinimumIdle(2);
            config.setConnectionTimeout(30000);
            config.setIdleTimeout(600000);
            config.setMaxLifetime(1800000);

            dataSource = new HikariDataSource(config);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to initialize connection pool", e);
        }
    }

    /*
     * =========================================================
     * Get Database Connection
     * =========================================================
     */

    public static Connection getConnection() {

        try {
            return dataSource.getConnection();

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to obtain database connection", e);
        }
    }

    /*
     * =========================================================
     * Close Connection Pool
     * =========================================================
     */

    public static void closePool() {

        if (dataSource != null) {
            dataSource.close();
        }
    }
}