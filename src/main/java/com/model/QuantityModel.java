package com.model;

import com.measurement.IMeasurable;
import com.measurement.Quantity;

/*
 * =========================================================
 * QuantityModel
 * =========================================================
 *
 * UC15 – Model Layer
 *
 * Purpose:
 * Internal representation of a quantity used inside
 * the Service layer.
 *
 * This separates internal processing from external DTOs.
 *
 * Model -> used for computations
 * DTO   -> used for communication
 */

public class QuantityModel<U extends IMeasurable> {

    private Quantity<U> quantity;

    public QuantityModel(double value, U unit) {
        this.quantity = new Quantity<>(value, unit);
    }

    public Quantity<U> getQuantity() {
        return quantity;
    }

    public double getValue() {
        return quantity.getValue();
    }

    public U getUnit() {
        return quantity.getUnit();
    }

    @Override
    public String toString() {
        return quantity.toString();
    }
}
