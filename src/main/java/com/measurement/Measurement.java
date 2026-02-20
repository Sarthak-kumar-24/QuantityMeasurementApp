package com.measurement;

/**
 * Abstract base class for all measurements.
 * Enforces value-based equality and immutability.
 */
public abstract class Measurement {

    protected final double value;

    protected Measurement(double value) {
        this.value = value;
    }

    /**
     * Common equality logic for all measurements.
     * Ensures:
     * - same reference
     * - same concrete class
     * - value-based comparison
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Measurement other = (Measurement) obj;
        return Double.compare(this.value, other.value) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(value);
    }
}
