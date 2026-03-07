package com.measurement;

<<<<<<< Updated upstream
/**
 * Generic class representing a length quantity.
 * Eliminates duplication from UC1 and UC2.
=======
import java.util.Objects;

/*
 * Generic class representing a length quantity with unit.
 * UC3 + UC4 compliant: DRY, immutable, extensible.
>>>>>>> Stashed changes
 */
public class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {

<<<<<<< Updated upstream
=======
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            throw new IllegalArgumentException("Value must be a valid number");
        }

>>>>>>> Stashed changes
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        this.value = value;
        this.unit = unit;
    }

    /**
     * Converts the quantity to base unit (inch)
     */
    private double toBaseUnit() {
        return unit.toBaseUnit(value);
    }

<<<<<<< Updated upstream
    /**
     * Value-based equality comparison.
     * Handles cross-unit equality (e.g., 1 ft == 12 inch)
     */
=======
>>>>>>> Stashed changes
    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        QuantityLength other = (QuantityLength) obj;

        return Double.compare(
                this.toBaseUnit(),
                other.toBaseUnit()
        ) == 0;
    }

    @Override
    public int hashCode() {
        return Double.hashCode(toBaseUnit());
    }
}