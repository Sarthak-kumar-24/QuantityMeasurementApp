package com.service;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dto.QuantityDTO;
import com.measurement.LengthUnit;
import com.measurement.TemperatureUnit;
import com.measurement.WeightUnit;


/*
 * =========================================================
 * Service Layer Tests
 * =========================================================
 *
 * Purpose:
 * Validate business logic implemented in the service layer.
 *
 * The service layer test performs:
 *  - Equality (1 ft == 12 in)
 *  - Conversion (1 ft -> 12 in)
 *  - Addition
 *  - Subtraction
 *  - Division
 *  - Temperature arithmetic restriction
 */
class QuantityMeasurementServiceTest {

    private IQuantityMeasurementService service;
    private static final Logger logger =
            Logger.getLogger(QuantityMeasurementServiceTest.class.getName());

    @BeforeEach
    void setup() {
    	logger.info("Initializing QuantityMeasurementServiceImpl");
        service = new QuantityMeasurementServiceImpl();
    }
    
    /*
     * =========================================================
     * Test: Equality Comparison
     * =========================================================
     *
     * 1 foot == 12 inches
     */
    @Test
    void testCompareEqualityDifferentUnits() {

        logger.info("=========== START TEST: Compare Equality ===========");

        logger.info("STEP 1: Creating first quantity DTO (1 ft)");
        QuantityDTO<LengthUnit> q1 =  new QuantityDTO<>(1.0, LengthUnit.FEET);

        logger.info("STEP 2: Creating second quantity DTO (12 in)");
        QuantityDTO<LengthUnit> q2 =  new QuantityDTO<>(12.0, LengthUnit.INCHES);

        logger.info("STEP 3: Calling service.compare()");
        boolean result = service.compare(q1, q2);

        logger.info("STEP 4: Validating equality result");
        assertTrue(result);

        logger.info("RESULT: Comparison successful -> 1 ft == 12 in");
        logger.info("=========== TEST PASSED ===========");
    }
    
    /*
     * =========================================================
     * Test: Unit Conversion
     * =========================================================
     *
     * 1 foot -> 12 inches
     */
    @Test
    void testConversionFeetToInches() {
        logger.info("=========== START TEST: Unit Conversion ===========");

        logger.info("STEP 1: Creating quantity DTO (1 ft)");
        QuantityDTO<LengthUnit> q = new QuantityDTO<>(1.0, LengthUnit.FEET);

        logger.info("STEP 2: Calling service.convert() to INCHES");
        QuantityDTO<LengthUnit> result =  service.convert(q, LengthUnit.INCHES);

        logger.info("STEP 3: Validating conversion result");

        assertEquals(12.0, result.getValue(), 1e-6);

        logger.info("RESULT: 1 ft successfully converted to 12 inches");
        logger.info("=========== TEST PASSED ===========");
    }
    
    /*
     * =========================================================
     * Test: Addition with Different Units
     * =========================================================
     *
     * 1 ft + 12 in = 2 ft
     */
    @Test
    void testAdditionDifferentUnits() {

        logger.info("=========== START TEST: Addition ===========");
        logger.info("Running Test: testAdditionDifferentUnits");
        
        logger.info("STEP 1: Creating first quantity DTO (1 ft)");
        QuantityDTO<LengthUnit> q1 =  new QuantityDTO<>(1.0, LengthUnit.FEET);

        logger.info("STEP 2: Creating second quantity DTO (12 in)");
        QuantityDTO<LengthUnit> q2 =  new QuantityDTO<>(12.0, LengthUnit.INCHES);

        logger.info("STEP 3: Calling service.add()");
        QuantityDTO<LengthUnit> result =  service.add(q1, q2);

        logger.info("STEP 4: Validating addition result");

        assertEquals(2.0, result.getValue(), 1e-6);
        assertEquals(LengthUnit.FEET, result.getUnit());

        logger.info("RESULT: Addition successful -> 1 ft + 12 in = 2 ft");
        logger.info("=========== TEST PASSED ===========");
    }
    
    /*
     * =========================================================
     * Test: Division Operation
     * =========================================================
     *
     * 10 ft % 5 ft = 2
     */
    @Test
    void testDivision() {

        logger.info("=========== START TEST: Division ===========");

        logger.info("STEP 1: Creating first quantity DTO (10 ft)");
        QuantityDTO<LengthUnit> q1 =  new QuantityDTO<>(10.0, LengthUnit.FEET);

        logger.info("STEP 2: Creating second quantity DTO (5 ft)");
        QuantityDTO<LengthUnit> q2 =  new QuantityDTO<>(5.0, LengthUnit.FEET);

        logger.info("STEP 3: Calling service.divide()");
        double result = service.divide(q1, q2);

        logger.info("STEP 4: Validating division result");

        assertEquals(2.0, result, 1e-6);

        logger.info("RESULT: Division successful -> 10 ft / 5 ft = 2");
        logger.info("=========== TEST PASSED ===========");
    }
    
    /*
     * =========================================================
     * Test: Subtraction Operation
     * =========================================================
     *
     * 5 ft − 12 in = 4 ft
     */
    @Test
    void testSubtractionDifferentUnits() {


        logger.info("=========== START TEST: Subtraction ===========");

        logger.info("STEP 1: Creating first quantity DTO (5 ft)");
        QuantityDTO<LengthUnit> q1 =  new QuantityDTO<>(5.0, LengthUnit.FEET);

        logger.info("STEP 2: Creating second quantity DTO (12 in)");
        QuantityDTO<LengthUnit> q2 =   new QuantityDTO<>(12.0, LengthUnit.INCHES);

        logger.info("STEP 3: Calling service.subtract()");
        QuantityDTO<LengthUnit> result =  service.subtract(q1, q2);

        logger.info("STEP 4: Validating subtraction result");

        assertEquals(4.0, result.getValue(), 1e-6);

        logger.info("RESULT: Subtraction successful -> 5 ft - 12 in = 4 ft");
        logger.info("=========== TEST PASSED ===========");
    }

    /*
     * =========================================================
     * Test: Temperature Arithmetic Restriction
     * =========================================================
     *
     * Temperature does not support arithmetic operations.
     * Attempting addition should throw an exception.
     */ 
    @Test
    void testTemperatureAdditionBlocked() {
        logger.info("=========== START TEST: Temperature Restriction ===========");

        logger.info("STEP 1: Creating temperature DTO objects");

        QuantityDTO<TemperatureUnit> t1 =  new QuantityDTO<>(30, TemperatureUnit.CELSIUS);

        QuantityDTO<TemperatureUnit> t2 =  new QuantityDTO<>(20, TemperatureUnit.CELSIUS);

        logger.info("STEP 2: Attempting invalid addition");

        assertThrows(RuntimeException.class,
                () -> service.add(t1, t2));

        logger.info("RESULT: Exception correctly thrown for temperature addition");
        logger.info("=========== TEST PASSED ===========");
    }
    
    /*
     * =========================================================
     * Test: Cross-Category Arithmetic Restriction
     * =========================================================
     *
     * Length and Weight cannot be added together.
     * Example:
     * 1 ft + 1 kg -> invalid operation
     */

    @Test
    void testCrossCategoryAdditionBlocked() {

        logger.info("=========== START TEST: Cross Category Addition ===========");

        logger.info("STEP 1: Creating length DTO (1 ft)");
        QuantityDTO<LengthUnit> length =  new QuantityDTO<>(1.0, LengthUnit.FEET);

        logger.info("STEP 2: Creating weight DTO (1 kg)");
        QuantityDTO<WeightUnit> weight =   new QuantityDTO<>(1.0, WeightUnit.KILOGRAM);

        logger.info("STEP 3: Attempting invalid addition (Length + Weight)");

        assertThrows(RuntimeException.class,
                () -> service.add((QuantityDTO)length, (QuantityDTO)weight));

        logger.info("RESULT: Exception correctly thrown for cross-category arithmetic");
        logger.info("=========== TEST PASSED ===========");
    }
    
    
    /*
     * =========================================================
     * Test: Divide By Zero Restriction
     * =========================================================
     *
     * Division by zero should not be allowed.
     * Example:
     * 10 ft % 0 ft -> error
     */

    @Test
    void testDivideByZero() {

        logger.info("=========== START TEST: Divide By Zero ===========");

        logger.info("STEP 1: Creating first quantity DTO (10 ft)");
        QuantityDTO<LengthUnit> q1 =  new QuantityDTO<>(10.0, LengthUnit.FEET);

        logger.info("STEP 2: Creating second quantity DTO (0 ft)");
        QuantityDTO<LengthUnit> q2 =  new QuantityDTO<>(0.0, LengthUnit.FEET);

        logger.info("STEP 3: Attempting division by zero");

        assertThrows(RuntimeException.class,
                () -> service.divide(q1, q2));

        logger.info("RESULT: Exception correctly thrown for divide-by-zero");
        logger.info("=========== TEST PASSED ===========");
    }
    
    
    /*
     * =========================================================
     * Test: Equality False Case
     * =========================================================
     *
     * Two different quantities should not be equal.
     * Example:
     * 1 ft != 2 ft
     */

    @Test
    void testEqualityFalse() {

        logger.info("=========== START TEST: Equality False Case ===========");

        logger.info("STEP 1: Creating first quantity DTO (1 ft)");
        QuantityDTO<LengthUnit> q1 =  new QuantityDTO<>(1.0, LengthUnit.FEET);

        logger.info("STEP 2: Creating second quantity DTO (2 ft)");
        QuantityDTO<LengthUnit> q2 =  new QuantityDTO<>(2.0, LengthUnit.FEET);

        logger.info("STEP 3: Calling service.compare()");

        boolean result = service.compare(q1, q2);

        logger.info("STEP 4: Performing assertion");

        assertTrue(!result);

        logger.info("RESULT: Comparison correctly returned false");
        logger.info("=========== TEST PASSED ===========");
    }
    
    
}
