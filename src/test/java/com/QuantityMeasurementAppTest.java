package com;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.measurement.LengthUnit;
import com.measurement.QuantityLength;

/**
 * UC7 Test Class
 * Covers:
 * - Equality (UC3 / UC4)
 * - Conversion (UC5)
 * - Addition implicit target (UC6)
 * - Addition explicit target (UC7)
 */
public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    /* ==================================================
       UC3 / UC4 : EQUALITY
       ================================================== */

    @Test
    void testEquality_CrossUnit() {
        assertEquals(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES)
        );
    }

    @Test
    void testEquality_SameReference() {
        QuantityLength q = new QuantityLength(2.0, LengthUnit.YARDS);
        assertEquals(q, q);
    }

    /* ==================================================
       UC5 : CONVERSION
       ================================================== */

    @Test
    void testConversion_FeetToInches() {
        assertEquals(
                12.0,
                QuantityLength.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES),
                EPSILON
        );
    }

    @Test
    void testConversion_RoundTrip() {
        double value = 5.0;
        double inches = QuantityLength.convert(value, LengthUnit.FEET, LengthUnit.INCHES);
        double feet = QuantityLength.convert(inches, LengthUnit.INCHES, LengthUnit.FEET);

        assertEquals(value, feet, EPSILON);
    }

    /* ==================================================
       UC6 : ADDITION (implicit target)
       ================================================== */

    @Test
    void testAddition_ImplicitTarget_FeetPlusInches() {
        QuantityLength result =
                new QuantityLength(1.0, LengthUnit.FEET)
                        .add(new QuantityLength(12.0, LengthUnit.INCHES));

        assertEquals(new QuantityLength(2.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_Commutative_Implicit() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        assertEquals(a.add(b), b.add(a));
    }

    /* ==================================================
       UC7 : ADDITION (explicit target unit)
       ================================================== */

    @Test
    void testAddition_ExplicitTarget_Feet() {
        QuantityLength result =
                QuantityLength.add(
                        new QuantityLength(1.0, LengthUnit.FEET),
                        new QuantityLength(12.0, LengthUnit.INCHES),
                        LengthUnit.FEET
                );

        assertEquals(new QuantityLength(2.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_ExplicitTarget_Inches() {
        QuantityLength result =
                QuantityLength.add(
                        new QuantityLength(1.0, LengthUnit.FEET),
                        new QuantityLength(12.0, LengthUnit.INCHES),
                        LengthUnit.INCHES
                );

        assertEquals(new QuantityLength(24.0, LengthUnit.INCHES), result);
    }

    @Test
    void testAddition_ExplicitTarget_Yards() {
        QuantityLength result =
                QuantityLength.add(
                        new QuantityLength(1.0, LengthUnit.FEET),
                        new QuantityLength(12.0, LengthUnit.INCHES),
                        LengthUnit.YARDS
                );

        assertEquals(0.666666, result.getValue(), 1e-3);
        assertEquals(LengthUnit.YARDS, result.getUnit());
    }

    @Test
    void testAddition_ExplicitTarget_Centimeters() {
        QuantityLength result =
                QuantityLength.add(
                        new QuantityLength(2.54, LengthUnit.CENTIMETERS),
                        new QuantityLength(1.0, LengthUnit.INCHES),
                        LengthUnit.CENTIMETERS
                );

        assertEquals(5.08, result.getValue(), 1e-2);
    }

    @Test
    void testAddition_ExplicitTarget_WithZero() {
        QuantityLength result =
                QuantityLength.add(
                        new QuantityLength(5.0, LengthUnit.FEET),
                        new QuantityLength(0.0, LengthUnit.INCHES),
                        LengthUnit.YARDS
                );

        assertEquals(1.666666, result.getValue(), 1e-3);
    }

    @Test
    void testAddition_ExplicitTarget_NegativeValues() {
        QuantityLength result =
                QuantityLength.add(
                        new QuantityLength(5.0, LengthUnit.FEET),
                        new QuantityLength(-2.0, LengthUnit.FEET),
                        LengthUnit.INCHES
                );

        assertEquals(36.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTarget_NullTarget_Throws() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityLength.add(
                        new QuantityLength(1.0, LengthUnit.FEET),
                        new QuantityLength(1.0, LengthUnit.FEET),
                        null
                ));
    }
}