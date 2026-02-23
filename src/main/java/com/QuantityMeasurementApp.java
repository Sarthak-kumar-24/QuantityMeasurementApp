package com;

import java.util.Scanner;

import com.measurement.*;
import service.EqualityService;

/**
 * Quantity Measurement App
 * Supports:
 * UC1–UC8: Length
 * UC9: Weight
 */
public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        EqualityService equalityService = new EqualityService();

        System.out.println("=== Quantity Measurement App (UC1 → UC9) ===");
        System.out.println("Choose Measurement Category:");
        System.out.println("1. Length");
        System.out.println("2. Weight");
        System.out.print("Enter choice: ");

        int category = scanner.nextInt();
        scanner.nextLine();

        switch (category) {

            /* ===============================
               LENGTH (UC1 → UC8)
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

                QuantityLength l1 = new QuantityLength(lv1, lu1);
                QuantityLength l2 = new QuantityLength(lv2, lu2);

                System.out.println("\nEquality: " +
                        equalityService.areEqual(l1, l2));

                System.out.println("Addition (implicit): " +
                        l1.add(l2));

                System.out.print("Target unit for addition: ");
                LengthUnit lTarget = LengthUnit.fromString(scanner.nextLine());
                System.out.println("Addition (explicit): " +
                        QuantityLength.add(l1, l2, lTarget));

                System.out.print("Convert first quantity to: ");
                LengthUnit lConv = LengthUnit.fromString(scanner.nextLine());
                System.out.println("Conversion: " +
                        l1.convertTo(lConv));
                break;

            /* ===============================
               WEIGHT (UC9)
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

                QuantityWeight w1 = new QuantityWeight(wv1, wu1);
                QuantityWeight w2 = new QuantityWeight(wv2, wu2);

                System.out.println("\nEquality: " +
                        equalityService.areEqual(w1, w2));

                System.out.println("Addition (implicit): " +
                        w1.add(w2));

                System.out.print("Target unit for addition: ");
                WeightUnit wTarget = WeightUnit.fromString(scanner.nextLine());
                System.out.println("Addition (explicit): " +
                        QuantityWeight.add(w1, w2, wTarget));

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