package com.measurement;

import java.util.Objects;

/**
 * UC8: QuantityLength
 * - No conversion logic
 * - Delegates everything to LengthUnit
 * - Preserves UC3–UC7 APIs
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
       UC5 – Conversion
       =============================== */

    public QuantityLength convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double base = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(base);

        return new QuantityLength(converted, targetUnit);
    }

    public static double convert(double value, LengthUnit source, LengthUnit target) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }
        if (source == null || target == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }

        double base = source.convertToBaseUnit(value);
        return target.convertFromBaseUnit(base);
    }

    /* ===============================
       UC6 – Addition (implicit target)
       =============================== */

    public QuantityLength add(QuantityLength other) {
        if (other == null) {
            throw new IllegalArgumentException("Other quantity cannot be null");
        }
        return add(this, other, this.unit);
    }

    /* ===============================
       UC7 – Addition (explicit target)
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

        double baseSum =
                q1.unit.convertToBaseUnit(q1.value)
              + q2.unit.convertToBaseUnit(q2.value);

        double result = targetUnit.convertFromBaseUnit(baseSum);
        return new QuantityLength(result, targetUnit);
    }

    /* ===============================
       UC3 – Equality
       =============================== */

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
        return Objects.hash(Math.round(
                unit.convertToBaseUnit(value) / EPSILON));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}