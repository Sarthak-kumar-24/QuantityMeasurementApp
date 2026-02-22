package com;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.measurement.LengthUnit;
import com.measurement.QuantityLength;

/**
 * UC5 Test Class
 * Tests explicit unit-to-unit conversion API
 */
public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    /* ===============================
       BASIC CONVERSION TESTS
       =============================== */

    @Test
    void testConversion_FeetToInches() {
        double result = QuantityLength.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES);
        assertEquals(12.0, result, EPSILON);
    }

    @Test
    void testConversion_InchesToFeet() {
        double result = QuantityLength.convert(24.0, LengthUnit.INCHES, LengthUnit.FEET);
        assertEquals(2.0, result, EPSILON);
    }

    @Test
    void testConversion_YardsToInches() {
        double result = QuantityLength.convert(1.0, LengthUnit.YARDS, LengthUnit.INCHES);
        assertEquals(36.0, result, EPSILON);
    }

    @Test
    void testConversion_InchesToYards() {
        double result = QuantityLength.convert(72.0, LengthUnit.INCHES, LengthUnit.YARDS);
        assertEquals(2.0, result, EPSILON);
    }

    @Test
    void testConversion_CentimetersToInches() {
        double result = QuantityLength.convert(2.54, LengthUnit.CENTIMETERS, LengthUnit.INCHES);
        assertEquals(1.0, result, EPSILON);
    }

    @Test
    void testConversion_FeetToYards() {
        double result = QuantityLength.convert(6.0, LengthUnit.FEET, LengthUnit.YARDS);
        assertEquals(2.0, result, EPSILON);
    }

    /* ===============================
       ROUND TRIP CONVERSION
       =============================== */

    @Test
    void testConversion_RoundTrip_PreservesValue() {
        double original = 5.0;

        double toInches = QuantityLength.convert(original, LengthUnit.FEET, LengthUnit.INCHES);
        double backToFeet = QuantityLength.convert(toInches, LengthUnit.INCHES, LengthUnit.FEET);

        assertEquals(original, backToFeet, EPSILON);
    }

    /* ===============================
       EDGE CASES
       =============================== */

    @Test
    void testConversion_ZeroValue() {
        double result = QuantityLength.convert(0.0, LengthUnit.FEET, LengthUnit.INCHES);
        assertEquals(0.0, result, EPSILON);
    }

    @Test
    void testConversion_NegativeValue() {
        double result = QuantityLength.convert(-1.0, LengthUnit.FEET, LengthUnit.INCHES);
        assertEquals(-12.0, result, EPSILON);
    }

    /* ===============================
       INVALID INPUT HANDLING
       =============================== */

    @Test
    void testConversion_NullSourceUnit_Throws() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityLength.convert(1.0, null, LengthUnit.INCHES));
    }

    @Test
    void testConversion_NullTargetUnit_Throws() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityLength.convert(1.0, LengthUnit.FEET, null));
    }

    @Test
    void testConversion_NaN_Throws() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityLength.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCHES));
    }

    @Test
    void testConversion_InfiniteValue_Throws() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityLength.convert(Double.POSITIVE_INFINITY, LengthUnit.FEET, LengthUnit.INCHES));
    }

    /* ===============================
       PRECISION TEST
       =============================== */

    @Test
    void testConversion_PrecisionTolerance() {
        double result = QuantityLength.convert(1.0, LengthUnit.CENTIMETERS, LengthUnit.INCHES);
        assertEquals(0.393701, result, EPSILON);
    }
}