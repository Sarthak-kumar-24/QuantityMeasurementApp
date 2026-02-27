package com;

import java.util.Scanner;

import com.measurement.LengthUnit;
import com.measurement.QuantityLength;
import service.EqualityService;

/*
 * UC4: Extended Unit Support
 * Supports Feet, Inches, Yards, and Centimeters.
 */
public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        EqualityService equalityService = new EqualityService();

        System.out.println("=== Quantity Measurement App (UC4) ===");

        // User input
        System.out.print("Enter first value: ");
        double value1 = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter first unit (ft, in, yd, cm): ");
        String unitInput1 = scanner.nextLine();

        System.out.print("Enter second value: ");
        double value2 = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter second unit (ft, in, yd, cm): ");
        String unitInput2 = scanner.nextLine();

        // Convert to enum
        LengthUnit unit1 = LengthUnit.fromString(unitInput1);
        LengthUnit unit2 = LengthUnit.fromString(unitInput2);

        // Create quantities
        QuantityLength q1 = new QuantityLength(value1, unit1);
        QuantityLength q2 = new QuantityLength(value2, unit2);

        // Equality check
        boolean result = equalityService.areEqual(q1, q2);

        System.out.println("\n--- Result ---");
        System.out.println(q1 + " == " + q2 + " ? " + result);

        scanner.close();
    }
}