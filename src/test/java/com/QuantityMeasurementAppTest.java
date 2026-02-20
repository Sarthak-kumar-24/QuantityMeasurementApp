package com;

import org.junit.jupiter.api.Test;

import com.measurement.Feet;

import service.EqualityService;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

	private final EqualityService equalityService = new EqualityService();

	@Test
	void testFeetEquality_SameValue() {
		Feet f1 = new Feet(1.0);
		Feet f2 = new Feet(1.0);

		assertTrue(equalityService.areEqual(f1, f2));
	}

	@Test
	void testFeetEquality_DifferentValue() {
		Feet f1 = new Feet(1.0);
		Feet f2 = new Feet(2.0);

		assertFalse(equalityService.areEqual(f1, f2));
	}

	@Test
	void testFeetEquality_NullComparison() {
		Feet f1 = new Feet(1.0);

		assertFalse(equalityService.areEqual(f1, null));
	}

	@Test
	void testFeetEquality_DifferentClass() {
		Feet f1 = new Feet(1.0);
		String other = "1.0";

		assertFalse(equalityService.areEqual(f1, other));
	}

	@Test
	void testFeetEquality_SameReference() {
		Feet f1 = new Feet(1.0);

		assertTrue(equalityService.areEqual(f1, f1));
	}
}
