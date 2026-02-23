package com.measurement;

/**
 * VolumeUnit
 *
 * UC11: Volume Measurement Support
 * Base unit: LITRE
 */
public enum VolumeUnit implements IMeasurable {

    LITRE(1.0),
    MILLILITRE(0.001),
    GALLON(3.78541);

    /** Conversion factor to base unit (litre) */
    private final double toLitreFactor;

    VolumeUnit(double toLitreFactor) {
        this.toLitreFactor = toLitreFactor;
    }

    /* ===============================
       IMeasurable Implementation
       =============================== */

    @Override
    public double getConversionFactor() {
        return toLitreFactor;
    }

    @Override
    public String getUnitName() {
        return name();
    }

    /* ===============================
       Backward-style helpers
       =============================== */

    public double convertToBaseUnit(double value) {
        return value * toLitreFactor;
    }

    public double convertFromBaseUnit(double baseValue) {
        return baseValue / toLitreFactor;
    }

    public static VolumeUnit fromString(String unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }

        switch (unit.trim().toLowerCase()) {
            case "l":
            case "litre":
            case "liter":
                return LITRE;
            case "ml":
            case "millilitre":
            case "milliliter":
                return MILLILITRE;
            case "gal":
            case "gallon":
                return GALLON;
            default:
                throw new IllegalArgumentException("Invalid volume unit: " + unit);
        }
    }
}