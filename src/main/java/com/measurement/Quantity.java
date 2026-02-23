package com.measurement;

import java.util.Objects;

/**
 * UC10: Generic Quantity class
 */
public final class Quantity<U extends IMeasurable> extends Measurement {

    private static final double EPSILON = 1e-6;
    private final U unit;

    public Quantity(double value, U unit) {
        super(value);
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.unit = unit;
    }

    public U getUnit() {
        return unit;
    }

    /* Conversion */
    public Quantity<U> convertTo(U target) {
        double base = unit.convertToBaseUnit(value);
        return new Quantity<>(target.convertFromBaseUnit(base), target);
    }

    /* Addition (implicit target) */
    public Quantity<U> add(Quantity<U> other) {
        return add(this, other, this.unit);
    }

    /* Addition (explicit target) */
    public static <U extends IMeasurable> Quantity<U> add(
            Quantity<U> q1,
            Quantity<U> q2,
            U target
    ) {
        double baseSum =
                q1.unit.convertToBaseUnit(q1.value)
              + q2.unit.convertToBaseUnit(q2.value);

        return new Quantity<>(
                target.convertFromBaseUnit(baseSum),
                target
        );
    }

    /* Equality (category-safe) */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Quantity<?>)) return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (!unit.getClass().equals(other.unit.getClass())) {
            return false;
        }

        double b1 = unit.convertToBaseUnit(value);
        double b2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(b1 - b2) < EPSILON;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                unit.getClass(),
                Math.round(unit.convertToBaseUnit(value) / EPSILON)
        );
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}