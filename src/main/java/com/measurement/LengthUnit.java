package com.measurement;

public enum LengthUnit {

    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double toFeetFactor;

    LengthUnit(double toFeetFactor) {
        this.toFeetFactor = toFeetFactor;
    }

    public double convertToBaseUnit(double value) {
        return value * toFeetFactor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toFeetFactor;
    }

    public static LengthUnit fromString(String unit) {
        switch (unit.trim().toLowerCase()) {
            case "ft": case "feet": return FEET;
            case "in": case "inch": return INCHES;
            case "yd": case "yard": return YARDS;
            case "cm": case "centimeter": return CENTIMETERS;
            default: throw new IllegalArgumentException("Invalid length unit");
        }
    }
}