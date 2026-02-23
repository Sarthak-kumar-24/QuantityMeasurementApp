package com.measurement;

/**
 * WeightUnit
 *
 * - UC9 : Standalone enum for weight conversion
 * - UC10: Implements IMeasurable for generic Quantity<U>
 *
 * Base unit: KILOGRAM
 */
public enum WeightUnit implements IMeasurable {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    /** Conversion factor relative to base unit (kilogram) */
    private final double toKgFactor;

    WeightUnit(double toKgFactor) {
        this.toKgFactor = toKgFactor;
    }

    /* =====================================================
       UC10 – IMeasurable implementation
       ===================================================== */

    @Override
    public double getConversionFactor() {
        return toKgFactor;
    }

    @Override
    public String getUnitName() {
        return name();
    }

    /* =====================================================
       UC9 – Backward compatibility helpers
       ===================================================== */

    public double convertToBaseUnit(double value) {
        return IMeasurable.super.convertToBaseUnit(value);
    }

    public double convertFromBaseUnit(double baseValue) {
        return IMeasurable.super.convertFromBaseUnit(baseValue);
    }

    /**
     * Parses string input into WeightUnit.
     * Required for UC9 console app compatibility.
     */
    public static WeightUnit fromString(String unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        switch (unit.trim().toLowerCase()) {
            case "kg":
            case "kilogram":
                return KILOGRAM;
            case "g":
            case "gram":
                return GRAM;
            case "lb":
            case "pound":
            case "pounds":
                return POUND;
            default:
                throw new IllegalArgumentException("Invalid weight unit: " + unit);
        }
    }
}