package com.measurement;

/**
 * Represents an Inches measurement.
 */
public class Inches extends Measurement {

    public Inches(double value) {
        super(value);
    }
    
    @Override
    protected double toBaseUnit() {
        return value;
    }
}
