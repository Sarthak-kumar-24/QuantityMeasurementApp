package com;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.measurement.LengthUnit;
import com.measurement.QuantityLength;

import service.EqualityService;

/**
 * UC4 Test Class
 * Tests generic QuantityLength equality across
 * FEET, INCHES, YARDS, and CENTIMETERS
 */
class QuantityMeasurementAppTest {

    private final EqualityService equalityService = new EqualityService();

    /* ===============================
       SAME UNIT EQUALITY TESTS
       =============================== */

    @Test
    void testFeetEquality_SameValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(1.0, LengthUnit.FEET);

        assertTrue(equalityService.areEqual(q1, q2));
    }

    @Test
    void testInchesEquality_SameValue() {
        QuantityLength q1 = new QuantityLength(10.0, LengthUnit.INCHES);
        QuantityLength q2 = new QuantityLength(10.0, LengthUnit.INCHES);

        assertTrue(equalityService.areEqual(q1, q2));
    }

    @Test
    void testYardsEquality_SameValue() {
        QuantityLength q1 = new QuantityLength(2.0, LengthUnit.YARDS);
        QuantityLength q2 = new QuantityLength(2.0, LengthUnit.YARDS);

        assertTrue(equalityService.areEqual(q1, q2));
    }

    @Test
    void testCentimetersEquality_SameValue() {
        QuantityLength q1 = new QuantityLength(50.0, LengthUnit.CENTIMETERS);
        QuantityLength q2 = new QuantityLength(50.0, LengthUnit.CENTIMETERS);

        assertTrue(equalityService.areEqual(q1, q2));
    }

    /* ===============================
       DIFFERENT VALUE TESTS
       =============================== */

    @Test
    void testFeetEquality_DifferentValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength q2 = new QuantityLength(2.0, LengthUnit.FEET);

        assertFalse(equalityService.areEqual(q1, q2));
    }

    @Test
    void testYardsEquality_DifferentValue() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength q2 = new QuantityLength(2.0, LengthUnit.YARDS);

        assertFalse(equalityService.areEqual(q1, q2));
    }

    /* ===============================
       CROSS UNIT EQUALITY TESTS
       =============================== */

    @Test
    void testFeetToInches_Equality() {
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches = new QuantityLength(12.0, LengthUnit.INCHES);

        assertTrue(equalityService.areEqual(feet, inches));
    }

    @Test
    void testYardsToFeet_Equality() {
        QuantityLength yards = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength feet = new QuantityLength(3.0, LengthUnit.FEET);

        assertTrue(equalityService.areEqual(yards, feet));
    }

    @Test
    void testYardsToInches_Equality() {
        QuantityLength yards = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength inches = new QuantityLength(36.0, LengthUnit.INCHES);

        assertTrue(equalityService.areEqual(yards, inches));
    }

    @Test
    void testCentimetersToInches_Equality() {
        QuantityLength cm = new QuantityLength(1.0, LengthUnit.CENTIMETERS);
        QuantityLength inches = new QuantityLength(0.393701, LengthUnit.INCHES);

        assertTrue(equalityService.areEqual(cm, inches));
    }

    /* ===============================
       TRANSITIVE PROPERTY TEST
       =============================== */

    @Test
    void testTransitiveProperty_MultiUnit() {
        QuantityLength yards = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength feet = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength inches = new QuantityLength(36.0, LengthUnit.INCHES);

        assertTrue(equalityService.areEqual(yards, feet));
        assertTrue(equalityService.areEqual(feet, inches));
        assertTrue(equalityService.areEqual(yards, inches));
    }

    /* ===============================
       NULL & TYPE SAFETY TESTS
       =============================== */

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

    /* ===============================
       SAME REFERENCE TEST
       =============================== */

    @Test
    void testSameReference() {
        QuantityLength q1 = new QuantityLength(1.0, LengthUnit.FEET);

        assertTrue(equalityService.areEqual(q1, q1));
    }
}