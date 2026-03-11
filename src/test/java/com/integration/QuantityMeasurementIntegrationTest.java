package com.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.logging.Logger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.controller.QuantityMeasurementController;
import com.dto.QuantityDTO;
import com.measurement.LengthUnit;
import com.measurement.WeightUnit;
import com.service.IQuantityMeasurementService;
import com.service.QuantityMeasurementServiceImpl;

/* Integration layers test includes
 * 
 * - End-to-end addition
 * - End-to-end conversion
 * - DTO immutability
 * 
 */
class QuantityMeasurementIntegrationTest {

    private IQuantityMeasurementService service;
    private QuantityMeasurementController controller;

    private static final Logger logger =
            Logger.getLogger(QuantityMeasurementIntegrationTest.class.getName());

    @BeforeEach
    void setup() {

        logger.info("Initializing Service and Controller for Integration Test");

        service = new QuantityMeasurementServiceImpl();
        controller = new QuantityMeasurementController(service);
    }
	
	
    /*
     * =========================================================
     * Test: End-to-End Length Addition
     * =========================================================
     *
     * Flow:
     * Controller -> Service -> Quantity -> Repository
     *
     * Example:
     * 1 ft + 12 in = 2 ft
     */
	@Test
	void testEndToEndLengthAddition() {

        logger.info("=========== START TEST: End-to-End Length Addition ===========");

        logger.info("STEP 1: Creating first DTO (1 ft)");
        QuantityDTO<LengthUnit> q1 = new QuantityDTO<>(1.0, LengthUnit.FEET);

        logger.info("STEP 2: Creating second DTO (12 in)");
        QuantityDTO<LengthUnit> q2 =  new QuantityDTO<>(12.0, LengthUnit.INCHES);

        logger.info("STEP 3: Calling controller.add()");
        QuantityDTO<LengthUnit> result = controller.add(q1, q2);

        logger.info("STEP 4: Validating result");

        assertEquals(2.0, result.getValue(), 1e-6);
        assertEquals(LengthUnit.FEET, result.getUnit());

        logger.info("RESULT: Addition successful -> 1 ft + 12 in = 2 ft");
        logger.info("=========== TEST PASSED ===========");
	}
	
	
    /*
     * =========================================================
     * Test: End-to-End Unit Conversion
     * =========================================================
     *
     * Example:
     * 1 ft -> 12 in
     */
	@Test
	void testEndToEndConversion() {

        logger.info("=========== START TEST: End-to-End Conversion ===========");

        logger.info("STEP 1: Creating DTO (1 ft)");
        QuantityDTO<LengthUnit> q =
                new QuantityDTO<>(1.0, LengthUnit.FEET);

        logger.info("STEP 2: Calling controller.convert()");
        QuantityDTO<LengthUnit> result =
                controller.convert(q, LengthUnit.INCHES);

        logger.info("STEP 3: Validating conversion result");

        assertEquals(12.0, result.getValue(), 1e-6);

        logger.info("RESULT: Conversion successful -> 1 ft = 12 in");
        logger.info("=========== TEST PASSED ===========");
	}
	
    /*
     * =========================================================
     * Test: Immutability Validation
     * =========================================================
     *
     * Ensures that DTO objects remain unchanged
     * after service operations.
     *
     * Example:
     * q1 + q2 should produce a new object,
     * while q1 remains unchanged.
     */
	@Test
	void testQuantityImmutability() {

        logger.info("=========== START TEST: DTO Immutability ===========");

        logger.info("STEP 1: Creating DTO objects");

        QuantityDTO<WeightUnit> q1 =
                new QuantityDTO<>(1.0, WeightUnit.KILOGRAM);

        QuantityDTO<WeightUnit> q2 =
                new QuantityDTO<>(1.0, WeightUnit.KILOGRAM);

        logger.info("STEP 2: Calling service.add()");
        QuantityDTO<WeightUnit> result =
                service.add(q1, q2);

        logger.info("STEP 3: Validating immutability");

        assertEquals(1.0, q1.getValue(), 1e-6);
        assertEquals(2.0, result.getValue(), 1e-6);

        logger.info("RESULT: DTO immutability confirmed");
        logger.info("=========== TEST PASSED ===========");
	}
}
