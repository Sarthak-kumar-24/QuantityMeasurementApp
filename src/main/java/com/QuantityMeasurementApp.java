package com;

import java.util.Scanner;

import com.measurement.*;
import service.EqualityService;

/**
 * Quantity Measurement App (UC10 – Pure Generic Version)
 *
 * Supports:
 * - Length via Quantity<LengthUnit>
 * - Weight via Quantity<WeightUnit>
 */
public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        EqualityService equalityService = new EqualityService();

        System.out.println("=== Quantity Measurement App (UC10) ===");
        System.out.println("Choose Measurement Category:");
        System.out.println("1. Length");
        System.out.println("2. Weight");
        System.out.print("Enter choice: ");

        int category = scanner.nextInt();
        scanner.nextLine();

        switch (category) {

            /* ===============================
               LENGTH
               =============================== */
            case 1:
                System.out.println("\n--- Length Measurement ---");
                System.out.println("Units: ft, in, yd, cm");

                System.out.print("Enter first value: ");
                double lv1 = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("Enter first unit: ");
                LengthUnit lu1 = LengthUnit.fromString(scanner.nextLine());

                System.out.print("Enter second value: ");
                double lv2 = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("Enter second unit: ");
                LengthUnit lu2 = LengthUnit.fromString(scanner.nextLine());

                Quantity<LengthUnit> l1 = new Quantity<>(lv1, lu1);
                Quantity<LengthUnit> l2 = new Quantity<>(lv2, lu2);

                System.out.println("\nEquality: " +
                        equalityService.areEqual(l1, l2));

                System.out.println("Addition (implicit): " +
                        l1.add(l2));

                System.out.print("Target unit for addition: ");
                LengthUnit lTarget = LengthUnit.fromString(scanner.nextLine());
                System.out.println("Addition (explicit): " +
                        Quantity.add(l1, l2, lTarget));

                System.out.print("Convert first quantity to: ");
                LengthUnit lConv = LengthUnit.fromString(scanner.nextLine());
                System.out.println("Conversion: " +
                        l1.convertTo(lConv));
                break;

            /* ===============================
               WEIGHT
               =============================== */
            case 2:
                System.out.println("\n--- Weight Measurement ---");
                System.out.println("Units: kg, g, lb");

                System.out.print("Enter first value: ");
                double wv1 = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("Enter first unit: ");
                WeightUnit wu1 = WeightUnit.fromString(scanner.nextLine());

                System.out.print("Enter second value: ");
                double wv2 = scanner.nextDouble();
                scanner.nextLine();
                System.out.print("Enter second unit: ");
                WeightUnit wu2 = WeightUnit.fromString(scanner.nextLine());

                Quantity<WeightUnit> w1 = new Quantity<>(wv1, wu1);
                Quantity<WeightUnit> w2 = new Quantity<>(wv2, wu2);

                System.out.println("\nEquality: " +
                        equalityService.areEqual(w1, w2));

                System.out.println("Addition (implicit): " +
                        w1.add(w2));

                System.out.print("Target unit for addition: ");
                WeightUnit wTarget = WeightUnit.fromString(scanner.nextLine());
                System.out.println("Addition (explicit): " +
                        Quantity.add(w1, w2, wTarget));

                System.out.print("Convert first quantity to: ");
                WeightUnit wConv = WeightUnit.fromString(scanner.nextLine());
                System.out.println("Conversion: " +
                        w1.convertTo(wConv));
                break;

            default:
                System.out.println("Invalid category selection");
        }

        scanner.close();
    }
}