package com.measurement;

public enum WeightUnit {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double toKgFactor;

    WeightUnit(double toKgFactor) {
        this.toKgFactor = toKgFactor;
    }

    public double convertToBaseUnit(double value) {
        return value * toKgFactor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toKgFactor;
    }

    public static WeightUnit fromString(String unit) {
        switch (unit.trim().toLowerCase()) {
            case "kg": case "kilogram": return KILOGRAM;
            case "g": case "gram": return GRAM;
            case "lb": case "pound": return POUND;
            default: throw new IllegalArgumentException("Invalid weight unit");
        }
    }
}