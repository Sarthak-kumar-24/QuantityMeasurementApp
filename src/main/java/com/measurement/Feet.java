package com.measurement;

/**
 * Represents a Feet measurement.
 */
public class Feet extends Measurement {

    public Feet(double value) {
        super(value);
    }
    
    @Override
    protected double toBaseUnit() {
        return value * 12;
    }
}
