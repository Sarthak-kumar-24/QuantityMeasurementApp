package com.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.dto.QuantityDTO;
import com.measurement.LengthUnit;
import com.service.IQuantityMeasurementService;
import com.service.QuantityMeasurementServiceImpl;

/*
 * =========================================================
 * Controller Layer Tests
 * =========================================================
 *
 * Purpose:
 * Verify that the controller correctly delegates requests
 * to the service layer and returns correct responses.
 *
 * These tests DO NOT validate business logic directly.
 * They validate:
 *  - Controller -> Service interaction
 *  - Correct result forwarding
 *
 * Architecture Flow Tested:
 *
 * Test -> Controller -> Service -> Quantity -> Result
 * 
 * Controller layer test includes
 * - Compare
 * - Add
 * - Convert
 */
class QuantityMeasurementControllerTest {

    private QuantityMeasurementController controller;

    // logger for systemetic output display on console
    private static final Logger logger =
            Logger.getLogger(QuantityMeasurementControllerTest.class.getName());

    @BeforeEach
    void setup() {

        logger.info("========== TEST SETUP ==========");
        logger.info("Initializing Service and Controller");

        IQuantityMeasurementService service =  new QuantityMeasurementServiceImpl();

        controller =  new QuantityMeasurementController(service);
    }
    
    
    /*
     * =========================================================
     * Test: Controller Compare Operation
     * =========================================================
     *
     * Verifies that the controller correctly delegates
     * equality comparison to the service layer.
     *
     * Example:
     * 1 foot == 12 inches
     */
    @Test
    void testControllerCompare() {

        logger.info("=========== START TEST: Controller Compare ===========");

        logger.info("STEP 1: Creating first DTO (1 ft)");
        QuantityDTO<LengthUnit> q1 =  new QuantityDTO<>(1.0, LengthUnit.FEET);

        logger.info("STEP 2: Creating second DTO (12 in)");
        QuantityDTO<LengthUnit> q2 =  new QuantityDTO<>(12.0, LengthUnit.INCHES);

        logger.info("STEP 3: Calling controller.compare()");
        boolean result = controller.compare(q1, q2);

        
        logger.info("STEP 4: Performing assertion");

        assertTrue(result);

        logger.info("RESULT: Comparison successful -> 1 ft == 12 in");
        logger.info("=========== TEST PASSED ===========");
    }
    
    /*
     * =========================================================
     * Test: Controller Addition
     * =========================================================
     *
     * Verifies that the controller correctly forwards
     * addition requests to the service layer.
     *
     * Example:
     * 1 ft + 12 in = 2 ft
     */
    @Test
    void testControllerAdd() {

        logger.info("=========== START TEST: Controller Addition ===========");

        logger.info("STEP 1: Creating first DTO (1 ft)");
        QuantityDTO<LengthUnit> q1 =  new QuantityDTO<>(1.0, LengthUnit.FEET);

        logger.info("STEP 2: Creating second DTO (12 in)");
        QuantityDTO<LengthUnit> q2 = new QuantityDTO<>(12.0, LengthUnit.INCHES);

        logger.info("STEP 3: Calling controller.add()");
        QuantityDTO<LengthUnit> result =  controller.add(q1, q2);

        logger.info("STEP 4: Performing assertion");

        assertEquals(2.0, result.getValue(), 1e-6);

        logger.info("RESULT: Addition successful -> 1 ft + 12 in = 2 ft");
        logger.info("=========== TEST PASSED ===========");
    }
    
    
    /*
     * =========================================================
     * Test: Controller Conversion
     * =========================================================
     *
     * Verifies that unit conversion requests are
     * correctly passed to the service layer.
     *
     * Example:
     * 1 ft -> 12 inches
     */
    @Test
    void testControllerConvert() {

        logger.info("=========== START TEST: Controller Conversion ===========");

        logger.info("STEP 1: Creating DTO (1 ft)");
        QuantityDTO<LengthUnit> q = new QuantityDTO<>(1.0, LengthUnit.FEET);

        logger.info("STEP 2: Calling controller.convert()");
        QuantityDTO<LengthUnit> result = controller.convert(q, LengthUnit.INCHES);

        logger.info("STEP 3: Performing assertion");

        assertEquals(12.0, result.getValue(), 1e-6);

        logger.info("RESULT: Conversion successful -> 1 ft = 12 in");
        logger.info("=========== TEST PASSED ===========");
    }
}
