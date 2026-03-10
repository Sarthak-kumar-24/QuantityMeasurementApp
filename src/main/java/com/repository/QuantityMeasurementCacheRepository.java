package com.repository;

import java.util.ArrayList;
import java.util.List;

import com.entity.QuantityMeasurementEntity;

/*
 * =========================================================
 * Cache Repository Implementation
 * =========================================================
 *
 * UC15 – Repository Layer Implementation
 *
 * Purpose:
 * Stores measurement operations in an in-memory cache.
 *
 * Advantages:
 * - Fast retrieval
 * - Simple implementation
 * - Can be extended later for database support
 *
 * Design Pattern:
 * Singleton Pattern
 */

public class QuantityMeasurementCacheRepository
        implements IQuantityMeasurementRepository {

    private static QuantityMeasurementCacheRepository instance;

    private final List<QuantityMeasurementEntity> cache;

    /*
     * Private constructor for Singleton pattern
     */
    private QuantityMeasurementCacheRepository() {
        cache = new ArrayList<>();
    }

    /*
     * Get repository instance
     */
    public static synchronized QuantityMeasurementCacheRepository getInstance() {

        if (instance == null) {
            instance = new QuantityMeasurementCacheRepository();
        }

        return instance;
    }

    /*
     * Save operation result into cache
     */
    @Override
    public void save(QuantityMeasurementEntity entity) {
        cache.add(entity);
    }

    /*
     * Return all stored measurement operations
     */
    @Override
    public List<QuantityMeasurementEntity> findAll() {
        return new ArrayList<>(cache);
    }
}
