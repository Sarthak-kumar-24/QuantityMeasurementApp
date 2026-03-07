package com.measurement;

/*
 * Enum representing supported length units.
 * Base unit chosen: INCH
 */
public enum LengthUnit {

    FEET(12.0),
    INCH(1.0);

    private final double conversionFactorToInch;

    LengthUnit(double conversionFactorToInch) {
        this.conversionFactorToInch = conversionFactorToInch;
    }

    /**
     * Converts a value of this unit to base unit (inch)
     */
    public double toBaseUnit(double value) {
        return value * conversionFactorToInch;
    }

    /**
     * Parses user input safely to LengthUnit
     */
    public static LengthUnit fromString(String unit) {
        return LengthUnit.valueOf(unit.toUpperCase());
    }
}