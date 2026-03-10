package com.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/*
 * =========================================================
 * QuantityMeasurementEntity
 * =========================================================
 *
 * UC15 – Entity Layer
 *
 * Purpose:
 * Represents a stored record of a quantity measurement operation.
 *
 * Stored information:
 * - Operand 1
 * - Operand 2
 * - Operation type
 * - Result
 * - Error message (if any)
 * - Timestamp
 *
 * This entity can be persisted in:
 * - memory cache
 * - file
 * - database (future extension)
 */

public class QuantityMeasurementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String operand1;
    private String operand2;
    private String operation;

    private String result;
    private String error;

    private LocalDateTime timestamp;

    /*
     * Constructor for successful operations
     */
    public QuantityMeasurementEntity(
            String operand1,
            String operand2,
            String operation,
            String result) {

        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operation = operation;
        this.result = result;
        this.timestamp = LocalDateTime.now();
    }

    /*
     * Constructor for error cases
     */
    public QuantityMeasurementEntity(
            String operand1,
            String operand2,
            String operation,
            String error,
            boolean isError) {

        this.operand1 = operand1;
        this.operand2 = operand2;
        this.operation = operation;
        this.error = error;
        this.timestamp = LocalDateTime.now();
    }

    public String getOperand1() {
        return operand1;
    }

    public String getOperand2() {
        return operand2;
    }

    public String getOperation() {
        return operation;
    }

    public String getResult() {
        return result;
    }

    public String getError() {
        return error;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public boolean hasError() {
        return error != null;
    }

    @Override
    public String toString() {

        if (hasError()) {
            return "Operation: " + operation +
                    " | Error: " + error +
                    " | Time: " + timestamp;
        }

        return operand1 +
                " " + operation +
                " " + operand2 +
                " = " + result +
                " | Time: " + timestamp;
    }
}
