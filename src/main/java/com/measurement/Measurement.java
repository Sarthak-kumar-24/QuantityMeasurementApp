package com.measurement;

/**
 * Abstract base class for all measurements.
 * Enforces immutability and value-based equality contract.
 */
public abstract class Measurement {

    protected final double value;

    protected Measurement(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}