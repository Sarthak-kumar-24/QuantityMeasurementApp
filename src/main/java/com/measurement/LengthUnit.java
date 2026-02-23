package com.measurement;

/**
 * LengthUnit
 *
 * - UC1–UC8: Standalone enum for length conversion
 * - UC10   : Implements IMeasurable for generic Quantity<U>
 *
 * Base unit: FEET
 */
public enum LengthUnit implements IMeasurable {

    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    /** Conversion factor relative to base unit (feet) */
    private final double toFeetFactor;

    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    /* =====================================================
       UC10 – IMeasurable implementation
       ===================================================== */

    @Override
    public double getConversionFactor() {
        return toFeetFactor;
    }

    @Override
    public String getUnitName() {
        return name();
    }

    /* =====================================================
       UC1–UC8 – Backward compatibility helpers
       (delegate to IMeasurable default methods)
       ===================================================== */

    public double convertToBaseUnit(double value) {
        return IMeasurable.super.convertToBaseUnit(value);
    }

    public double convertFromBaseUnit(double baseValue) {
        return IMeasurable.super.convertFromBaseUnit(baseValue);
    }

    /**
     * Parses string input into LengthUnit.
     * Required for UC8 console app compatibility.
     */
    public static LengthUnit fromString(String unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        switch (unit.trim().toLowerCase()) {
            case "ft":
            case "feet":
            case "foot":
                return FEET;
            case "in":
            case "inch":
            case "inches":
                return INCHES;
            case "yd":
            case "yard":
            case "yards":
                return YARDS;
            case "cm":
            case "centimeter":
            case "centimeters":
                return CENTIMETERS;
            default:
                throw new IllegalArgumentException("Invalid length unit: " + unit);
        }
    }
}