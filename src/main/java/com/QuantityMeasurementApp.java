package com;

import java.util.Scanner;

import com.measurement.LengthUnit;
import com.measurement.QuantityLength;
import service.EqualityService;

/**
 * UC6: Equality + Conversion + Addition
 */
public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        EqualityService equalityService = new EqualityService();

        System.out.println("=== Quantity Measurement App (UC6) ===");
        System.out.println("Units: ft, in, yd, cm\n");

        // First quantity
        System.out.print("Enter first value: ");
        double v1 = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter first unit: ");
        LengthUnit u1 = LengthUnit.fromString(scanner.nextLine());

        // Second quantity
        System.out.print("Enter second value: ");
        double v2 = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter second unit: ");
        LengthUnit u2 = LengthUnit.fromString(scanner.nextLine());

        QuantityLength q1 = new QuantityLength(v1, u1);
        QuantityLength q2 = new QuantityLength(v2, u2);

        System.out.println("\n--- Equality ---");
        System.out.println(q1 + " == " + q2 + " ? "
                + equalityService.areEqual(q1, q2));

        System.out.println("\n--- Addition (UC6) ---");
        QuantityLength sum = q1.add(q2);
        System.out.println(q1 + " + " + q2 + " = " + sum);

        System.out.println("\n--- Conversion (UC5) ---");
        System.out.print("Convert first quantity to unit: ");
        LengthUnit target = LengthUnit.fromString(scanner.nextLine());
        System.out.println(q1 + " = " + q1.convertTo(target));

        scanner.close();
    }
}