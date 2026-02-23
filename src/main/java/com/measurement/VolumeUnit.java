package com.measurement;

public enum VolumeUnit implements IMeasurable {

    LITRE(1.0),
    MILLILITRE(0.001),
    GALLON(3.78541);

    private final double toLitre;

    VolumeUnit(double toLitre) {
        this.toLitre = toLitre;
    }

    @Override
    public double getConversionFactor() {
        return toLitre;
    }

    @Override
    public String getUnitName() {
        return name();
    }

    public static VolumeUnit fromString(String unit) {
        return switch (unit.trim().toLowerCase()) {
            case "l" -> LITRE;
            case "ml" -> MILLILITRE;
            case "gal" -> GALLON;
            default -> throw new IllegalArgumentException("Invalid volume unit");
        };
    }
}