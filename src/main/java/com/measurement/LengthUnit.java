package com.measurement;

/**
 * Enum representing supported length units.
 * Base unit = INCHES
 */
public enum LengthUnit {

    INCHES(1.0),
    FEET(12.0),
    YARDS(36.0),
    CENTIMETERS(0.393701);

    private final double toInchesFactor;

    LengthUnit(double toInchesFactor) {
        this.toInchesFactor = toInchesFactor;
    }

    public double toInches(double value) {
        return value * toInchesFactor;
    }

    public double fromInches(double inches) {
        return inches / toInchesFactor;
    }

    public static LengthUnit fromString(String unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        switch (unit.trim().toLowerCase()) {
            case "in":
            case "inch":
            case "inches":
                return INCHES;
            case "ft":
            case "foot":
            case "feet":
                return FEET;
            case "yd":
            case "yard":
            case "yards":
                return YARDS;
            case "cm":
            case "centimeter":
            case "centimeters":
                return CENTIMETERS;
            default:
                throw new IllegalArgumentException("Unsupported unit: " + unit);
        }
    }
}