package com.measurement;

/**
 * Enum representing supported length units.
 * All conversion factors are defined relative to BASE UNIT = INCHES.
 */
public enum LengthUnit {

    INCHES(1.0),          // Base unit
    FEET(12.0),           // 1 ft = 12 inches
    YARDS(36.0),          // 1 yd = 36 inches
    CENTIMETERS(0.393701); // 1 cm = 0.393701 inches

    private final double conversionFactorToInches;

    LengthUnit(double conversionFactorToInches) {
        this.conversionFactorToInches = conversionFactorToInches;
    }

    /**
     * Converts a given value of this unit to inches.
     */
    public double toInches(double value) {
        return value * conversionFactorToInches;
    }

    /**
     * Converts user input string to LengthUnit enum.
     */
    public static LengthUnit fromString(String unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        switch (unit.toLowerCase()) {
            case "inch":
            case "inches":
            case "in":
                return INCHES;

            case "feet":
            case "foot":
            case "ft":
                return FEET;

            case "yard":
            case "yards":
            case "yd":
                return YARDS;

            case "centimeter":
            case "centimeters":
            case "cm":
                return CENTIMETERS;

            default:
                throw new IllegalArgumentException("Unsupported unit: " + unit);
        }
    }
}