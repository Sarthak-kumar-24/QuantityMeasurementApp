package com;

import java.util.Scanner;

import com.measurement.LengthUnit;
import com.measurement.QuantityLength;
import service.EqualityService;

/**
 * UC8 Demo App
 */
public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        EqualityService equalityService = new EqualityService();

        System.out.println("=== Quantity Measurement App (UC8) ===");
        System.out.println("Units: ft, in, yd, cm\n");

        System.out.print("Enter first value: ");
        double v1 = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter first unit: ");
        LengthUnit u1 = LengthUnit.fromString(scanner.nextLine());

        System.out.print("Enter second value: ");
        double v2 = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter second unit: ");
        LengthUnit u2 = LengthUnit.fromString(scanner.nextLine());

        QuantityLength q1 = new QuantityLength(v1, u1);
        QuantityLength q2 = new QuantityLength(v2, u2);

        System.out.println("\n--- Equality ---");
        System.out.println(equalityService.areEqual(q1, q2));

        System.out.println("\n--- Addition (implicit) ---");
        System.out.println(q1.add(q2));

        System.out.println("\n--- Addition (explicit target) ---");
        System.out.print("Target unit: ");
        LengthUnit target = LengthUnit.fromString(scanner.nextLine());
        System.out.println(QuantityLength.add(q1, q2, target));

        System.out.println("\n--- Conversion ---");
        System.out.print("Convert first quantity to: ");
        LengthUnit conv = LengthUnit.fromString(scanner.nextLine());
        System.out.println(q1.convertTo(conv));

        scanner.close();
    }
}