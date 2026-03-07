package com;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.measurement.LengthUnit;
import com.measurement.QuantityLength;

import service.EqualityService;

/**
 * UC3 Test Class
 * Tests equality using generic QuantityLength
 */
class QuantityMeasurementAppTest {

	private final EqualityService equalityService = new EqualityService();

	@Test
	void testFeetEquality_SameValue() {
		QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
		QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);

		assertTrue(equalityService.areEqual(q1, q2));
	}

	@Test
	void testFeetEquality_DifferentValue() {
		QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
		QuantityLength q2 = new QuantityLength(2.0, LengthUnit.FEET);

		assertFalse(equalityService.areEqual(q1, q2));
	}

	@Test
	void testInchEquality_SameValue() {
		QuantityLength q1 = new QuantityLength(10.0, LengthUnit.INCH);
		QuantityLength q2 = new QuantityLength(10.0, LengthUnit.INCH);

		assertTrue(equalityService.areEqual(q1, q2));
	}
	@Test
	void testInchEquality_DifferentValue() {
	    QuantityLength q1 = new QuantityLength(10.0, LengthUnit.INCH);
	    QuantityLength q2 = new QuantityLength(20.0, LengthUnit.INCH);

	    assertFalse(equalityService.areEqual(q1, q2));
	}

	@Test
	void testCrossUnitEquality_FeetAndInch() {
		QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
		QuantityLength inch = new QuantityLength(12.0, LengthUnit.INCH);

		assertTrue(equalityService.areEqual(feet, inch));
	}

	@Test
	void testNullComparison() {
		QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);

		assertFalse(equalityService.areEqual(q1, null));
	}

	@Test
	void testDifferentClassComparison() {
		QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
		String other = "1 FEET";

		assertFalse(equalityService.areEqual(q1, other));
	}

	@Test
	void testSameReference() {
		QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);

		assertTrue(equalityService.areEqual(q1, q1));
	}
}