package com;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.measurement.*;
import service.EqualityService;

/**
 * Quantity Measurement App Test
 *
 * Covers: UC10 : Generic Quantity<U extends IMeasurable>
 */
public class QuantityMeasurementAppTest {

	private static final double EPSILON = 1e-6;

	/*
	 * ===================================================== Generic Equality –
	 * Length =====================================================
	 */

	@Test
	void testLengthEquality_CrossUnit() {
		Quantity<LengthUnit> q1 = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<LengthUnit> q2 = new Quantity<>(12.0, LengthUnit.INCHES);

		assertEquals(q1, q2);
	}

	/*
	 * ===================================================== Generic Equality –
	 * Weight =====================================================
	 */

	@Test
	void testWeightEquality_CrossUnit() {
		Quantity<WeightUnit> q1 = new Quantity<>(1.0, WeightUnit.KILOGRAM);
		Quantity<WeightUnit> q2 = new Quantity<>(1000.0, WeightUnit.GRAM);

		assertEquals(q1, q2);
	}

	/*
	 * ===================================================== Conversion
	 * =====================================================
	 */

	@Test
	void testLengthConversion() {
		Quantity<LengthUnit> q = new Quantity<>(1.0, LengthUnit.FEET).convertTo(LengthUnit.INCHES);

		assertEquals(12.0, q.getValue(), EPSILON);
	}

	@Test
	void testWeightConversion() {
		Quantity<WeightUnit> q = new Quantity<>(1.0, WeightUnit.KILOGRAM).convertTo(WeightUnit.GRAM);

		assertEquals(1000.0, q.getValue(), EPSILON);
	}

	/*
	 * ===================================================== Addition
	 * =====================================================
	 */

	@Test
	void testLengthAddition_Implicit() {
		Quantity<LengthUnit> result = new Quantity<>(1.0, LengthUnit.FEET).add(new Quantity<>(12.0, LengthUnit.INCHES));

		assertEquals(new Quantity<>(2.0, LengthUnit.FEET), result);
	}

	@Test
	void testWeightAddition_Explicit() {
		Quantity<WeightUnit> result = Quantity.add(new Quantity<>(1.0, WeightUnit.KILOGRAM),
				new Quantity<>(500.0, WeightUnit.GRAM), WeightUnit.GRAM);

		assertEquals(1500.0, result.getValue(), EPSILON);
		assertEquals(WeightUnit.GRAM, result.getUnit());
	}

	/*
	 * ===================================================== Cross-Category Safety
	 * =====================================================
	 */

	@Test
	void testLengthVsWeight_NotEqual() {
		EqualityService service = new EqualityService();

		Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
		Quantity<WeightUnit> weight = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		assertFalse(service.areEqual(length, weight));
	}

	/*
	 * ===================================================== Immutability
	 * =====================================================
	 */

	@Test
	void testImmutability() {
		Quantity<WeightUnit> q = new Quantity<>(1.0, WeightUnit.KILOGRAM);

		q.add(new Quantity<>(1.0, WeightUnit.KILOGRAM));

		assertEquals(1.0, q.getValue(), EPSILON);
	}

	@Test
	void testVolumeEquality_LitreToMillilitre() {
		assertEquals(new Quantity<>(1.0, VolumeUnit.LITRE), new Quantity<>(1000.0, VolumeUnit.MILLILITRE));
	}

	@Test
	void testVolumeConversion_GallonToLitre() {
		Quantity<VolumeUnit> result = new Quantity<>(1.0, VolumeUnit.GALLON).convertTo(VolumeUnit.LITRE);

		assertEquals(3.78541, result.getValue(), EPSILON);
	}

	@Test
	void testVolumeAddition_ExplicitTarget() {
		Quantity<VolumeUnit> result = Quantity.add(new Quantity<>(1.0, VolumeUnit.LITRE),
				new Quantity<>(1000.0, VolumeUnit.MILLILITRE), VolumeUnit.MILLILITRE);

		assertEquals(2000.0, result.getValue(), EPSILON);
	}

	@Test
	void testVolumeVsLength_NotEqual() {
		assertNotEquals(new Quantity<>(1.0, VolumeUnit.LITRE), new Quantity<>(1.0, LengthUnit.FEET));
	}
}