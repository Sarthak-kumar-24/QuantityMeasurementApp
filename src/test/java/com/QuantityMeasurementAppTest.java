package com;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.measurement.LengthUnit;
import com.measurement.QuantityLength;

/**
 * UC8 Test Class
 * Covers UC1 → UC8 with refactored standalone LengthUnit
 */
public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    /* =====================================================
       UC1 / UC3 – Equality
       ===================================================== */

    @Test
    void testEquality_SameUnit() {
        assertEquals(
                new QuantityLength(5.0, LengthUnit.FEET),
                new QuantityLength(5.0, LengthUnit.FEET)
        );
    }

    @Test
    void testEquality_CrossUnit() {
        assertEquals(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES)
        );
    }

    @Test
    void testEquality_Transitive() {
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches = new QuantityLength(12.0, LengthUnit.INCHES);
        QuantityLength yards = new QuantityLength(1.0 / 3.0, LengthUnit.YARDS);

        assertEquals(feet, inches);
        assertEquals(inches, yards);
        assertEquals(feet, yards);
    }

    /* =====================================================
       UC5 – Conversion
       ===================================================== */

    @Test
    void testConversion_FeetToInches() {
        assertEquals(
                12.0,
                QuantityLength.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES),
                EPSILON
        );
    }

    @Test
    void testConversion_InchesToFeet() {
        assertEquals(
                1.0,
                QuantityLength.convert(12.0, LengthUnit.INCHES, LengthUnit.FEET),
                EPSILON
        );
    }

    @Test
    void testConversion_RoundTrip() {
        double value = 5.0;

        double inches =
                QuantityLength.convert(value, LengthUnit.FEET, LengthUnit.INCHES);
        double feet =
                QuantityLength.convert(inches, LengthUnit.INCHES, LengthUnit.FEET);

        assertEquals(value, feet, EPSILON);
    }

    @Test
    void testConversion_InvalidValue_Throws() {
        assertThrows(IllegalArgumentException.class, () ->
                QuantityLength.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCHES));
    }

    /* =====================================================
       UC6 – Addition (implicit target)
       ===================================================== */

    @Test
    void testAddition_Implicit_SameUnit() {
        QuantityLength result =
                new QuantityLength(2.0, LengthUnit.FEET)
                        .add(new QuantityLength(3.0, LengthUnit.FEET));

        assertEquals(new QuantityLength(5.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_Implicit_CrossUnit() {
        QuantityLength result =
                new QuantityLength(1.0, LengthUnit.FEET)
                        .add(new QuantityLength(12.0, LengthUnit.INCHES));

        assertEquals(new QuantityLength(2.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_Implicit_Commutative() {
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        assertEquals(a.add(b), b.add(a));
    }

    /* =====================================================
       UC7 – Addition (explicit target unit)
       ===================================================== */

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
    void testAddition_ExplicitTarget_WithZero() {
        QuantityLength result =
                QuantityLength.add(
                        new QuantityLength(5.0, LengthUnit.FEET),
                        new QuantityLength(0.0, LengthUnit.INCHES),
                        LengthUnit.FEET
                );

        assertEquals(new QuantityLength(5.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_ExplicitTarget_Negative() {
        QuantityLength result =
                QuantityLength.add(
                        new QuantityLength(5.0, LengthUnit.FEET),
                        new QuantityLength(-2.0, LengthUnit.FEET),
                        LengthUnit.INCHES
                );

        assertEquals(36.0, result.getValue(), EPSILON);
    }

    /* =====================================================
       UC8 – LengthUnit Responsibility & Refactor Validation
       ===================================================== */

    @Test
    void testLengthUnit_ConvertToBaseUnit() {
        assertEquals(1.0,
                LengthUnit.INCHES.convertToBaseUnit(12.0),
                EPSILON);
    }

    @Test
    void testLengthUnit_ConvertFromBaseUnit() {
        assertEquals(12.0,
                LengthUnit.INCHES.convertFromBaseUnit(1.0),
                EPSILON);
    }

    @Test
    void testQuantityLength_DelegatesConversionToUnit() {
        QuantityLength q = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength converted = q.convertTo(LengthUnit.INCHES);

        assertEquals(new QuantityLength(12.0, LengthUnit.INCHES), converted);
    }

    @Test
    void testBackwardCompatibility_UC5() {
        assertEquals(
                2.0,
                QuantityLength.convert(6.0, LengthUnit.FEET, LengthUnit.YARDS),
                EPSILON
        );
    }

    @Test
    void testImmutability() {
        QuantityLength q1 = new QuantityLength(5.0, LengthUnit.FEET);
        q1.add(new QuantityLength(1.0, LengthUnit.FEET));

        assertEquals(5.0, q1.getValue());
    }
}