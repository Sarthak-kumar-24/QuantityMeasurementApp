package com.measurement;

import java.util.Objects;

/**
 * Immutable value object representing a length.
 * 
 * UC5 Enhancements:
 * - Unit-to-unit conversion
 * - Equality still works exactly like UC4
 */
public final class QuantityLength extends Measurement {

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

    /**
     * Convert this quantity to inches (base unit).
     */
    private double toBaseUnit() {
        return unit.toInches(value);
    }

    /**
     * UC5 FEATURE:
     * Convert this QuantityLength to another unit.
     * Returns a NEW object (immutability preserved).
     */
    public QuantityLength convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }

        double inches = toBaseUnit();
        double convertedValue = targetUnit.fromInches(inches);

        return new QuantityLength(convertedValue, targetUnit);
    }

    /**
     * Static conversion API (raw values).
     */
    public static double convert(
            double value,
            LengthUnit sourceUnit,
            LengthUnit targetUnit
    ) {
        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException("Value must be finite");
        }
        if (sourceUnit == null || targetUnit == null) {
            throw new IllegalArgumentException("Units cannot be null");
        }

        double inches = sourceUnit.toInches(value);
        return targetUnit.fromInches(inches);
    }

    /**
     * Equality based on physical length.
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (!(obj instanceof QuantityLength)) return false;

        QuantityLength other = (QuantityLength) obj;

        return Double.compare(
                this.toBaseUnit(),
                other.toBaseUnit()
        ) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(toBaseUnit());
    }

    @Override
    public String toString() {
        return value + " " + unit;
    }
}