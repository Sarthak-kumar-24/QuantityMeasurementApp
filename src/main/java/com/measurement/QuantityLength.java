package com.measurement;

import java.util.Objects;

/**
 * Generic, immutable length value object.
 * Supports equality, conversion, and addition.
 */
public final class QuantityLength extends Measurement {

    private static final double EPSILON = 1e-6;
    private final LengthUnit unit;

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
       INTERNAL BASE UNIT LOGIC
       =============================== */

    private double toBaseInches() {
        return unit.toInches(value);
    }

    private static double validate(double v) {
        if (!Double.isFinite(v)) {
            throw new IllegalArgumentException("Value must be finite");
        }
        return v;
    }

    /* ===============================
       UC5 – CONVERSION
       =============================== */

    public QuantityLength convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double inches = toBaseInches();
        double converted = targetUnit.fromInches(inches);
        return new QuantityLength(converted, targetUnit);
    }

    public static double convert(double value, LengthUnit source, LengthUnit target) {
        validate(value);
        if (source == null || target == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }
        double inches = source.toInches(value);
        return target.fromInches(inches);
    }

    /* ===============================
       UC6 – ADDITION (implicit target = first operand)
       =============================== */

    public QuantityLength add(QuantityLength other) {
        if (other == null) {
            throw new IllegalArgumentException("Other quantity cannot be null");
        }
        return add(this, other, this.unit);
    }

    /* ===============================
       UC7 – ADDITION WITH EXPLICIT TARGET UNIT
       =============================== */

    public static QuantityLength add(
            QuantityLength q1,
            QuantityLength q2,
            LengthUnit targetUnit
    ) {
        if (q1 == null || q2 == null) {
            throw new IllegalArgumentException("Operands cannot be null");
        }
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double sumInches = q1.toBaseInches() + q2.toBaseInches();
        double result = targetUnit.fromInches(sumInches);

        return new QuantityLength(result, targetUnit);
    }

    /* ===============================
       EQUALITY (UC3)
       =============================== */

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        QuantityLength other = (QuantityLength) obj;
        return Math.abs(this.toBaseInches() - other.toBaseInches()) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Math.round(toBaseInches() / EPSILON));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}