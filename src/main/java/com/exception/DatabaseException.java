package com.exception;


/*
 * =========================================================
 * DatabaseException
 * =========================================================
 *
 * UC16 – Database Layer Exception
 *
 * Purpose:
 * Represents errors that occur during database operations.
 *
 * Examples:
 * - Connection failure
 * - SQL execution errors
 * - Transaction failures
 * - Connection pool issues
 *
 * Why this class exists:
 * Separates database-specific errors from general
 * quantity measurement errors.
 *
 * Exception Hierarchy:
 *
 * RuntimeException
 *        │
 * QuantityMeasurementException
 *        │
 *   DatabaseException
 *
 * This allows:
 * - Domain errors to be handled separately
 * - Database errors to be caught and logged properly
 */

public class DatabaseException extends QuantityMeasurementException {

    /*
     * Constructor for simple database error messages
     */
    public DatabaseException(String message) {
        super(message);
    }

    /*
     * Constructor for wrapping lower-level SQL exceptions
     */
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
