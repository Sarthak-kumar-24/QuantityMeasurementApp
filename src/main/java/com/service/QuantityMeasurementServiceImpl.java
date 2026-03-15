package com.service;

import com.dto.QuantityDTO;
import com.entity.QuantityMeasurementEntity;
import com.exception.QuantityMeasurementException;
import com.measurement.IMeasurable;
import com.measurement.Quantity;
import com.repository.IQuantityMeasurementRepository;
import com.repository.QuantityMeasurementCacheRepository;
import com.repository.QuantityMeasurementDatabaseRepository;
import com.util.DatabaseConfig;


/*
 * =========================================================
 * Quantity Measurement Service Implementation
 * =========================================================
 *
 * UC15 – Service Layer
 *
 * Responsibilities:
 * - Convert DTO -> Quantity
 * - Perform operations using Quantity domain logic
 * - Convert result back to DTO
 * - Store operation history in repository
 * 
 * UC16 – Service Layer with Repository Selection
 */

public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {

    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl() {
       // this.repository = QuantityMeasurementCacheRepository.getInstance();
    	
        String repoType = DatabaseConfig.getRepositoryType();

        if ("database".equalsIgnoreCase(repoType)) {
            this.repository = new QuantityMeasurementDatabaseRepository();
        } else {
            this.repository = QuantityMeasurementCacheRepository.getInstance();
        }
    }

    /*
     * =========================================================
     * Compare Quantities
     * =========================================================
     */
    @Override
    public <U extends IMeasurable> boolean compare(
            QuantityDTO<U> q1,
            QuantityDTO<U> q2) {

        try {

            Quantity<U> a = new Quantity<>(q1.getValue(), q1.getUnit());
            Quantity<U> b = new Quantity<>(q2.getValue(), q2.getUnit());

            boolean result = a.equals(b);

            repository.save(
                    new QuantityMeasurementEntity(
                            a.toString(),
                            b.toString(),
                            "COMPARE",
                            String.valueOf(result)));

            return result;

        } catch (Exception e) {

            repository.save(
                    new QuantityMeasurementEntity(
                            q1.toString(),
                            q2.toString(),
                            "COMPARE",
                            e.getMessage(),
                            true));

            throw new QuantityMeasurementException(e.getMessage());
        }
    }

    /*
     * =========================================================
     * Convert Quantity
     * =========================================================
     */
    @Override
    public <U extends IMeasurable> QuantityDTO<U> convert(
            QuantityDTO<U> dto,
            U targetUnit) {

        try {

            Quantity<U> quantity =
                    new Quantity<>(dto.getValue(), dto.getUnit());

            Quantity<U> converted =
                    quantity.convertTo(targetUnit);

            repository.save(
                    new QuantityMeasurementEntity(
                            quantity.toString(),
                            targetUnit.getUnitName(),
                            "CONVERT",
                            converted.toString()));

            return new QuantityDTO<>(
                    converted.getValue(),
                    converted.getUnit());

        } catch (Exception e) {

            throw new QuantityMeasurementException(e.getMessage());
        }
    }

    /*
     * =========================================================
     * Addition
     * =========================================================
     */
    @Override
    public <U extends IMeasurable> QuantityDTO<U> add(
            QuantityDTO<U> q1,
            QuantityDTO<U> q2) {

        try {

            Quantity<U> a = new Quantity<>(q1.getValue(), q1.getUnit());
            Quantity<U> b = new Quantity<>(q2.getValue(), q2.getUnit());

            Quantity<U> result = a.add(b);

            repository.save(
                    new QuantityMeasurementEntity(
                            a.toString(),
                            b.toString(),
                            "ADD",
                            result.toString()));

            return new QuantityDTO<>(
                    result.getValue(),
                    result.getUnit());

        } catch (Exception e) {

            throw new QuantityMeasurementException(e.getMessage());
        }
    }

    /*
     * =========================================================
     * Addition with Target Unit
     * =========================================================
     */
    @Override
    public <U extends IMeasurable> QuantityDTO<U> add(
            QuantityDTO<U> q1,
            QuantityDTO<U> q2,
            U targetUnit) {

        try {

            Quantity<U> a = new Quantity<>(q1.getValue(), q1.getUnit());
            Quantity<U> b = new Quantity<>(q2.getValue(), q2.getUnit());

            Quantity<U> result = a.add(b, targetUnit);

            repository.save(
                    new QuantityMeasurementEntity(
                            a.toString(),
                            b.toString(),
                            "ADD",
                            result.toString()));

            return new QuantityDTO<>(
                    result.getValue(),
                    result.getUnit());

        } catch (Exception e) {

            throw new QuantityMeasurementException(e.getMessage());
        }
    }

    /*
     * =========================================================
     * Subtraction
     * =========================================================
     */
    @Override
    public <U extends IMeasurable> QuantityDTO<U> subtract(
            QuantityDTO<U> q1,
            QuantityDTO<U> q2) {

        try {

            Quantity<U> a = new Quantity<>(q1.getValue(), q1.getUnit());
            Quantity<U> b = new Quantity<>(q2.getValue(), q2.getUnit());

            Quantity<U> result = a.subtract(b);

            repository.save(
                    new QuantityMeasurementEntity(
                            a.toString(),
                            b.toString(),
                            "SUBTRACT",
                            result.toString()));

            return new QuantityDTO<>(
                    result.getValue(),
                    result.getUnit());

        } catch (Exception e) {

            throw new QuantityMeasurementException(e.getMessage());
        }
    }

    /*
     * =========================================================
     * Division
     * =========================================================
     */
    @Override
    public <U extends IMeasurable> double divide(
            QuantityDTO<U> q1,
            QuantityDTO<U> q2) {

        try {

            Quantity<U> a = new Quantity<>(q1.getValue(), q1.getUnit());
            Quantity<U> b = new Quantity<>(q2.getValue(), q2.getUnit());

            double result = a.divide(b);

            repository.save(
                    new QuantityMeasurementEntity(
                            a.toString(),
                            b.toString(),
                            "DIVIDE",
                            String.valueOf(result)));

            return result;

        } catch (Exception e) {

            throw new QuantityMeasurementException(e.getMessage());
        }
    }
}