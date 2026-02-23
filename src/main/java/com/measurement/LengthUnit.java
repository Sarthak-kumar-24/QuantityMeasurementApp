package com.measurement;

public enum LengthUnit implements IMeasurable {

    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    private final double toFeet;

    LengthUnit(double toFeet) {
        this.toFeet = toFeet;
    }

    @Override
    public double getConversionFactor() {
        return toFeet;
    }

    @Override
    public String getUnitName() {
        return name();
    }

    public static LengthUnit fromString(String unit) {
        return switch (unit.trim().toLowerCase()) {
            case "ft", "feet" -> FEET;
            case "in", "inch" -> INCHES;
            case "yd", "yard" -> YARDS;
            case "cm", "centimeter" -> CENTIMETERS;
            default -> throw new IllegalArgumentException("Invalid length unit");
        };
    }
}