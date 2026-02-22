package com;

import java.util.Scanner;

import com.measurement.LengthUnit;
import com.measurement.QuantityLength;
import service.EqualityService;

/**
 * UC5: Unit-to-Unit Conversion + Equality
 */
public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        EqualityService equalityService = new EqualityService();

        System.out.println("=== Quantity Measurement App (UC5) ===");
        System.out.println("Units: ft, in, yd, cm\n");

        System.out.print("Enter value: ");
        double value = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter source unit: ");
        LengthUnit source = LengthUnit.fromString(scanner.nextLine());

        System.out.print("Enter target unit: ");
        LengthUnit target = LengthUnit.fromString(scanner.nextLine());

        // UC5 Conversion
        QuantityLength original = new QuantityLength(value, source);
        QuantityLength converted = original.convertTo(target);

        System.out.println("\n--- Conversion ---");
        System.out.println(original + " = " + converted);

        // Equality demo
        System.out.print("\nEnter second value for equality check: ");
        double value2 = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter unit: ");
        LengthUnit unit2 = LengthUnit.fromString(scanner.nextLine());

        QuantityLength q2 = new QuantityLength(value2, unit2);

        System.out.println("\n--- Equality ---");
        System.out.println(original + " == " + q2 + " ? "
                + equalityService.areEqual(original, q2));

        scanner.close();
    }
}