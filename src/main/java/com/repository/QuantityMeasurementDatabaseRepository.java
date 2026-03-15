package com.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.entity.QuantityMeasurementEntity;
import com.exception.DatabaseException;
import com.util.ConnectionPool;

/*
 * =========================================================
 * QuantityMeasurementDatabaseRepository
 * =========================================================
 *
 * UC16 – JDBC Database Repository Implementation
 *
 * Purpose:
 * Persists quantity measurement operations into a database.
 *
 * Features:
 * - Uses JDBC with PreparedStatement
 * - Prevents SQL Injection
 * - Stores measurement operations
 * - Retrieves stored history
 *
 * Database Tables Used:
 * - quantity_measurement_entity
 *
 * Design:
 * Implements IQuantityMeasurementRepository interface
 * 
 * operand1  = Quantity(1.0, FEET)
 * operand2  = Quantity(12.0, INCHES)
 * operation = ADD
 * result    = Quantity(2.0, FEET)
 * timestamp = current time
 */

public class QuantityMeasurementDatabaseRepository
        implements IQuantityMeasurementRepository {

    /*
     * =========================================================
     * SQL Queries
     * =========================================================
     */

    private static final String INSERT_OPERATION = """
            INSERT INTO quantity_measurement_entity
            (operand1, operand2, operation, result, error, timestamp)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

    private static final String FETCH_ALL = """
            SELECT operand1, operand2, operation, result, error, timestamp
            FROM quantity_measurement_entity
            ORDER BY timestamp DESC
            """;

    /*
     * =========================================================
     * Save Operation to Database
     * =========================================================
     */

    @Override
    public void save(QuantityMeasurementEntity entity) {

        try (Connection conn = ConnectionPool.getConnection();
             PreparedStatement statement =
                     conn.prepareStatement(INSERT_OPERATION)) {

            statement.setString(1, entity.getOperand1());
            statement.setString(2, entity.getOperand2());
            statement.setString(3, entity.getOperation());
            statement.setString(4, entity.getResult());
            statement.setString(5, entity.getError());
            statement.setTimestamp(6, Timestamp.valueOf(entity.getTimestamp()));

            statement.executeUpdate();

        } catch (Exception e) {

            throw new DatabaseException(
                    "Database save operation failed", e);
        }
    }

    /*
     * =========================================================
     * Retrieve All Stored Operations
     * =========================================================
     */

    @Override
    public List<QuantityMeasurementEntity> findAll() {

        List<QuantityMeasurementEntity> results = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(FETCH_ALL);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {

                String operand1 = rs.getString("operand1");
                String operand2 = rs.getString("operand2");
                String operation = rs.getString("operation");
                String result = rs.getString("result");
                String error = rs.getString("error");

                QuantityMeasurementEntity entity;

                if (error != null) {

                    entity = new QuantityMeasurementEntity(
                            operand1,
                            operand2,
                            operation,
                            error,
                            true);

                } else {

                    entity = new QuantityMeasurementEntity(
                            operand1,
                            operand2,
                            operation,
                            result);
                }

                results.add(entity);
            }

        } catch (Exception e) {

            throw new DatabaseException(
                    "Database read operation failed", e);
        }

        return results;
    }
}
