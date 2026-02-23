package com.measurement;

/*
 * UC10–UC14: Common interface for all measurable units
 */
public interface IMeasurable {

    double getConversionFactor();

    default double convertToBaseUnit(double value) {
        return value * getConversionFactor();
    }

    default double convertFromBaseUnit(double baseValue) {
        return baseValue / getConversionFactor();
    }

    String getUnitName();

    /* ===============================
       UC14 – Selective Arithmetic
       =============================== */

    /*
     * Whether this unit supports arithmetic operations.
     * Length, Weight, Volume -> true
     * Temperature -> false
     */
    default boolean supportsArithmetic() {
        return true;
    }

    default void validateOperationSupport(String operation) {
        if (!supportsArithmetic()) {
            throw new UnsupportedOperationException(
                "Operation '" + operation + "' not supported for unit: " + getUnitName()
            );
        }
    }
}