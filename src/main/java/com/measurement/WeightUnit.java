package com.measurement;

public enum WeightUnit implements IMeasurable {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    private final double toKg;

    WeightUnit(double toKg) {
        this.toKg = toKg;
    }

    @Override
    public double getConversionFactor() {
        return toKg;
    }

    @Override
    public String getUnitName() {
        return name();
    }

    public static WeightUnit fromString(String unit) {
        return switch (unit.trim().toLowerCase()) {
            case "kg" -> KILOGRAM;
            case "g" -> GRAM;
            case "lb" -> POUND;
            default -> throw new IllegalArgumentException("Invalid weight unit");
        };
    }
}