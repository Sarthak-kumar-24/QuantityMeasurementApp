package com.measurement;

/**
 * UC8: Standalone LengthUnit enum
 * Base unit = FEET
 */
public enum LengthUnit {

    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double toFeetFactor;

    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    /** Convert a value in this unit to base unit (feet) */
    public double convertToBaseUnit(double value) {
        return value * toFeetFactor;
    }

    /** Convert a value from base unit (feet) to this unit */
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toFeetFactor;
    }

    /** Safe parsing */
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
                throw new IllegalArgumentException("Unsupported unit: " + unit);
        }
    }
}