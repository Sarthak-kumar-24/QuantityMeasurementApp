package com;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.measurement.*;
import service.EqualityService;

/**
 * Quantity Measurement App Test
 * Covers:
 * UC1–UC8 : Length
 * UC9     : Weight
 */
public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    /* =====================================================
       UC1 / UC3 – Length Equality
       ===================================================== */

    @Test
    void testLengthEquality_SameUnit() {
        assertEquals(
                new QuantityLength(5.0, LengthUnit.FEET),
                new QuantityLength(5.0, LengthUnit.FEET)
        );
    }

    @Test
    void testLengthEquality_CrossUnit() {
        assertEquals(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES)
        );
    }

    @Test
    void testLengthEquality_Transitive() {
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inches = new QuantityLength(12.0, LengthUnit.INCHES);
        QuantityLength yards = new QuantityLength(1.0 / 3.0, LengthUnit.YARDS);

        assertEquals(feet, inches);
        assertEquals(inches, yards);
        assertEquals(feet, yards);
    }

    /* =====================================================
       UC5 – Length Conversion
       ===================================================== */

    @Test
    void testLengthConversion_FeetToInches() {
        assertEquals(
                12.0,
                QuantityLength.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES),
                EPSILON
        );
    }

    @Test
    void testLengthConversion_InchesToFeet() {
        assertEquals(
                1.0,
                QuantityLength.convert(12.0, LengthUnit.INCHES, LengthUnit.FEET),
                EPSILON
        );
    }

    @Test
    void testLengthConversion_RoundTrip() {
        double value = 5.0;

        double inches =
                QuantityLength.convert(value, LengthUnit.FEET, LengthUnit.INCHES);
        double feet =
                QuantityLength.convert(inches, LengthUnit.INCHES, LengthUnit.FEET);

        assertEquals(value, feet, EPSILON);
    }

    /* =====================================================
       UC6 / UC7 – Length Addition
       ===================================================== */

    @Test
    void testLengthAddition_Implicit() {
        QuantityLength result =
                new QuantityLength(1.0, LengthUnit.FEET)
                        .add(new QuantityLength(12.0, LengthUnit.INCHES));

        assertEquals(new QuantityLength(2.0, LengthUnit.FEET), result);
    }

    @Test
    void testLengthAddition_ExplicitTarget() {
        QuantityLength result =
                QuantityLength.add(
                        new QuantityLength(1.0, LengthUnit.FEET),
                        new QuantityLength(12.0, LengthUnit.INCHES),
                        LengthUnit.INCHES
                );

        assertEquals(new QuantityLength(24.0, LengthUnit.INCHES), result);
    }

    /* =====================================================
       UC8 – LengthUnit Responsibility
       ===================================================== */

    @Test
    void testLengthUnit_ToBaseUnit() {
        assertEquals(
                1.0,
                LengthUnit.INCHES.convertToBaseUnit(12.0),
                EPSILON
        );
    }

    @Test
    void testLengthUnit_FromBaseUnit() {
        assertEquals(
                12.0,
                LengthUnit.INCHES.convertFromBaseUnit(1.0),
                EPSILON
        );
    }

    /* =====================================================
       UC9 – Weight Equality
       ===================================================== */

    @Test
    void testWeightEquality_SameUnit() {
        assertEquals(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
        );
    }

    @Test
    void testWeightEquality_CrossUnit_KgToGram() {
        assertEquals(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1000.0, WeightUnit.GRAM)
        );
    }

    @Test
    void testWeightEquality_CrossUnit_KgToPound() {
        assertEquals(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(2.20462, WeightUnit.POUND)
        );
    }

    /* =====================================================
       UC9 – Weight Conversion
       ===================================================== */

    @Test
    void testWeightConversion_KgToGram() {
        QuantityWeight result =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                        .convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    void testWeightConversion_PoundToKg() {
        QuantityWeight result =
                new QuantityWeight(1.0, WeightUnit.POUND)
                        .convertTo(WeightUnit.KILOGRAM);

        assertEquals(0.453592, result.getValue(), EPSILON);
    }

    /* =====================================================
       UC9 – Weight Addition
       ===================================================== */

    @Test
    void testWeightAddition_Implicit() {
        QuantityWeight result =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                        .add(new QuantityWeight(500.0, WeightUnit.GRAM));

        assertEquals(
                new QuantityWeight(1.5, WeightUnit.KILOGRAM),
                result
        );
    }

    @Test
    void testWeightAddition_ExplicitTarget() {
        QuantityWeight result =
                QuantityWeight.add(
                        new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                        new QuantityWeight(500.0, WeightUnit.GRAM),
                        WeightUnit.GRAM
                );

        assertEquals(1500.0, result.getValue(), EPSILON);
        assertEquals(WeightUnit.GRAM, result.getUnit());
    }

    /* =====================================================
       UC9 – Category Incompatibility
       ===================================================== */

    @Test
    void testLengthVsWeight_NotEqual() {
        EqualityService service = new EqualityService();

        QuantityLength length =
                new QuantityLength(1.0, LengthUnit.FEET);
        QuantityWeight weight =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        assertFalse(service.areEqual(length, weight));
    }

    /* =====================================================
       Immutability (All Categories)
       ===================================================== */

    @Test
    void testWeightImmutability() {
        QuantityWeight w =
                new QuantityWeight(1.0, WeightUnit.KILOGRAM);

        w.add(new QuantityWeight(1.0, WeightUnit.KILOGRAM));

        assertEquals(1.0, w.getValue(), EPSILON);
    }
}