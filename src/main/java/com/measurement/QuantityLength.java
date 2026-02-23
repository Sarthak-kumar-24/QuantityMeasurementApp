package com.measurement;

import java.util.Objects;

/**
 * QuantityLength
 *
 * - Represents an immutable length measurement
 * - Delegates all unit conversion to LengthUnit (UC8)
 * - Supports equality, conversion, and addition (UC1–UC7)
 * - Base unit normalization ensures cross-unit correctness
 */
public final class QuantityLength extends Measurement {

    private static final double EPSILON = 1e-6;

    private final LengthUnit unit;

    /**
     * Constructs a QuantityLength with a value and unit.
     *
     * @param value numeric value (must be finite)
     * @param unit  length unit (must not be null)
     */
    public QuantityLength(double value, LengthUnit unit) {
        super(value);
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.unit = unit;
    }

    /**
     * Returns the unit of this quantity.
     */
    public LengthUnit getUnit() {
        return unit;
    }

    /* =====================================================
       UC5 – Conversion
       ===================================================== */

    /**
     * Converts this quantity to the target unit.
     *
     * @param target target length unit
     * @return new QuantityLength in target unit
     */
    public QuantityLength convertTo(LengthUnit target) {
        if (target == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseValue = unit.convertToBaseUnit(value);
        double convertedValue = target.convertFromBaseUnit(baseValue);

        return new QuantityLength(convertedValue, target);
    }

    /**
     * Static conversion helper (used in UC5 tests).
     */
    public static double convert(
            double value,
            LengthUnit source,
            LengthUnit target
    ) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }
        if (source == null || target == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }

        double baseValue = source.convertToBaseUnit(value);
        return target.convertFromBaseUnit(baseValue);
    }

    /* =====================================================
       UC6 – Addition (implicit target)
       ===================================================== */

    /**
     * Adds another QuantityLength.
     * Result unit = this.unit
     */
    public QuantityLength add(QuantityLength other) {
        if (other == null) {
            throw new IllegalArgumentException("Other quantity cannot be null");
        }
        return add(this, other, this.unit);
    }

    /* =====================================================
       UC7 – Addition (explicit target)
       ===================================================== */

    /**
     * Adds two QuantityLength objects and returns result
     * in the specified target unit.
     */
    public static QuantityLength add(
            QuantityLength q1,
            QuantityLength q2,
            LengthUnit target
    ) {
        if (q1 == null || q2 == null) {
            throw new IllegalArgumentException("Operands cannot be null");
        }
        if (target == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double baseSum =
                q1.unit.convertToBaseUnit(q1.value)
              + q2.unit.convertToBaseUnit(q2.value);

        double resultValue = target.convertFromBaseUnit(baseSum);
        return new QuantityLength(resultValue, target);
    }

    /* =====================================================
       UC3 / UC4 – Equality
       ===================================================== */

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityLength)) return false;

        QuantityLength other = (QuantityLength) obj;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                Math.round(unit.convertToBaseUnit(value) / EPSILON)
        );
    }

    /* =====================================================
       Utility
       ===================================================== */

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}