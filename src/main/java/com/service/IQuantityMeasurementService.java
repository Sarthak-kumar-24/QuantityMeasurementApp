package com.service;

import com.dto.QuantityDTO;
import com.measurement.IMeasurable;
import com.measurement.Quantity;

/*
 * =========================================================
 * Quantity Measurement Service Interface
 * =========================================================
 *
 * UC15 – Service Layer Contract
 *
 * Defines business operations that can be performed
 * on quantities.
 *
 * Controller interacts only with this interface,
 * not with the implementation.
 */

public interface IQuantityMeasurementService {

	<U extends IMeasurable> boolean compare(QuantityDTO<U> q1, QuantityDTO<U> q2);

	<U extends IMeasurable> QuantityDTO<U> convert(QuantityDTO<U> quantity, U targetUnit);

	<U extends IMeasurable> QuantityDTO<U> add(QuantityDTO<U> q1, QuantityDTO<U> q2);

	<U extends IMeasurable> QuantityDTO<U> add(QuantityDTO<U> q1, QuantityDTO<U> q2, U targetUnit);

	<U extends IMeasurable> QuantityDTO<U> subtract(QuantityDTO<U> q1, QuantityDTO<U> q2);

	<U extends IMeasurable> double divide(QuantityDTO<U> q1, QuantityDTO<U> q2);
}
