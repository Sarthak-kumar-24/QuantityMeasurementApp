package com.measurement;

import java.util.Objects;

public final class QuantityWeight extends Measurement {

    private static final double EPSILON = 1e-6;
    private final WeightUnit unit;

    public QuantityWeight(double value, WeightUnit unit) {
        super(value);
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        this.unit = unit;
    }

    public WeightUnit getUnit() {
        return unit;
    }

    /* Conversion */
    public QuantityWeight convertTo(WeightUnit target) {
        double base = unit.convertToBaseUnit(value);
        return new QuantityWeight(target.convertFromBaseUnit(base), target);
    }

    /* Addition (implicit) */
    public QuantityWeight add(QuantityWeight other) {
        return add(this, other, this.unit);
    }

    /* Addition (explicit) */
    public static QuantityWeight add(
            QuantityWeight q1,
            QuantityWeight q2,
            WeightUnit target
    ) {
        double baseSum =
                q1.unit.convertToBaseUnit(q1.value)
              + q2.unit.convertToBaseUnit(q2.value);

        return new QuantityWeight(target.convertFromBaseUnit(baseSum), target);
    }

    /* Equality */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof QuantityWeight)) return false;

        QuantityWeight other = (QuantityWeight) obj;
        double b1 = unit.convertToBaseUnit(value);
        double b2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(b1 - b2) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.convertToBaseUnit(value));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}