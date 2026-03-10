package com.controller;

import com.dto.QuantityDTO;
import com.measurement.IMeasurable;

import com.service.IQuantityMeasurementService;

/*
 * =========================================================
 * Quantity Measurement Controller
 * =========================================================
 *
 * UC15 – Controller Layer
 *
 * Purpose:
 * Acts as the bridge between the application layer
 * and the service layer.
 *
 * Responsibilities:
 * - Receive requests from the application
 * - Delegate business logic to the service layer
 * - Return processed results
 *
 * Design Principles:
 * - Thin Controller (no business logic)
 * - Delegation to Service Layer
 * - Follows Facade Pattern
 */

public class QuantityMeasurementController {

    private final IQuantityMeasurementService service;

    /*
     * Constructor-based Dependency Injection
     */
    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    /*
     * =========================================================
     * Equality Check
     * =========================================================
     */
    public <U extends IMeasurable> boolean compare(
    		QuantityDTO<U> q1,
    		QuantityDTO<U> q2) {

        return service.compare(q1, q2);
    }

    /*
     * =========================================================
     * Unit Conversion
     * =========================================================
     */
    public <U extends IMeasurable> QuantityDTO<U> convert(
    		QuantityDTO<U> quantity,
            U targetUnit) {

        return service.convert(quantity, targetUnit);
    }

    /*
     * =========================================================
     * Addition
     * =========================================================
     */
    public <U extends IMeasurable> QuantityDTO<U> add(
    		QuantityDTO<U> q1,
    		QuantityDTO<U> q2) {

        return service.add(q1, q2);
    }

    public <U extends IMeasurable> QuantityDTO<U> add(
    		QuantityDTO<U> q1,
    		QuantityDTO<U> q2,
            U targetUnit) {

        return service.add(q1, q2, targetUnit);
    }

    /*
     * =========================================================
     * Subtraction
     * =========================================================
     */
    public <U extends IMeasurable> QuantityDTO<U> subtract(
    		QuantityDTO<U> q1,
    		QuantityDTO<U> q2) {

        return service.subtract(q1, q2);
    }



    /*
     * =========================================================
     * Division
     * =========================================================
     */
    public <U extends IMeasurable> double divide(
    		QuantityDTO<U> q1,
    		QuantityDTO<U> q2) {

        return service.divide(q1, q2);
    }
}
