package com.measurement;

import java.util.Objects;

/**
 * Generic, immutable length quantity.
 * Supports equality, conversion, and addition.
 */
public final class QuantityLength extends Measurement {

    private final LengthUnit unit;
    private static final double EPSILON = 1e-6;

    public QuantityLength(double value, LengthUnit unit) {
        super(value);
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.unit = unit;
    }

    public LengthUnit getUnit() {
        return unit;
    }

    /* ===============================
       UC3 / UC4 : EQUALITY
       =============================== */

    private double toBaseUnit() {
        return unit.toInches(value);
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        QuantityLength other = (QuantityLength) obj;
        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toBaseUnit());
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }

    /* ===============================
       UC5 : CONVERSION
       =============================== */

    /** Static conversion API */
    public static double convert(double value,
                                 LengthUnit source,
                                 LengthUnit target) {

        if (source == null || target == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }

        double inches = source.toInches(value);
        return target.fromInches(inches);
    }

    /** Instance-based conversion */
    public QuantityLength convertTo(LengthUnit targetUnit) {
        double convertedValue = convert(this.value, this.unit, targetUnit);
        return new QuantityLength(convertedValue, targetUnit);
    }

    /* ===============================
       UC6 : ADDITION
       =============================== */

    /** Add two quantities, result in unit of first operand */
    public QuantityLength add(QuantityLength other) {

        if (other == null) {
            throw new IllegalArgumentException("Second operand cannot be null");
        }

        double sumInInches =
                this.toBaseUnit() + other.toBaseUnit();

        double resultValue =
                this.unit.fromInches(sumInInches);

        return new QuantityLength(resultValue, this.unit);
    }

    /** Static addition API (optional overload) */
    public static QuantityLength add(QuantityLength a, QuantityLength b) {
        if (a == null) {
            throw new IllegalArgumentException("First operand cannot be null");
        }
        return a.add(b);
    }
}