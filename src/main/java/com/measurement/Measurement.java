package com.measurement;

/**
 * Measurement
 *
 * Abstract base class for all measurements.
 *
 * Responsibilities:
 * - Enforces immutability
 * - Validates numeric value
 * - Acts as a common parent for:
 *   - QuantityLength (UC1–UC9 wrapper)
 *   - QuantityWeight (UC9 wrapper)
 *   - Quantity<U extends IMeasurable> (UC10 generic)
 */
public abstract class Measurement {

    protected final double value;

    /**
     * Constructs a Measurement with a validated numeric value.
     *
     * @param value numeric value (must be finite)
     */
    protected Measurement(double value) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }
        this.value = value;
    }

    /**
     * Returns the numeric value of the measurement.
     */
    public double getValue() {
        return value;
    }
}