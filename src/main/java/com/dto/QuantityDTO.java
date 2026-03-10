package com.dto;

import com.measurement.IMeasurable;

/*
 * =========================================================
 * QuantityDTO (Data Transfer Object)
 * =========================================================
 *
 * UC15 – DTO Layer
 *
 * Purpose:
 * Used for transferring quantity data between layers
 * (Controller ⇄ Service).
 *
 * Why DTO?
 * - Prevents exposing internal domain objects
 * - Standardizes input/output format
 * - Enables REST/API readiness
 *
 * Example:
 * value = 5
 * unit  = FEET
 */

public class QuantityDTO<U extends IMeasurable> {

    private double value;
    private U unit;

    public QuantityDTO(double value, U unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setUnit(U unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return "QuantityDTO{" +
                "value=" + value +
                ", unit=" + unit +
                '}';
    }
}
