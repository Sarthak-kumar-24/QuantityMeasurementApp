package com.repository;

import java.util.List;

import com.entity.QuantityMeasurementEntity;

/*
 * =========================================================
 * Repository Interface
 * =========================================================
 *
 * UC15 – Repository Layer
 *
 * Purpose:
 * Provides abstraction for persistence operations.
 *
 * This allows different implementations:
 * - In-memory cache
 * - File storage
 * - Database (future)
 */

public interface IQuantityMeasurementRepository {

    /*
     * Save a measurement operation
     */
    void save(QuantityMeasurementEntity entity);

    /*
     * Retrieve all stored measurement records
     */
    List<QuantityMeasurementEntity> findAll();
}
