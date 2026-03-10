package com.app;

import java.util.Scanner;

import com.controller.QuantityMeasurementController;
import com.dto.QuantityDTO;
import com.measurement.LengthUnit;
import com.measurement.TemperatureUnit;
import com.measurement.VolumeUnit;
import com.measurement.WeightUnit;
import com.service.IQuantityMeasurementService;
import com.service.QuantityMeasurementServiceImpl;

/*
 * =========================================================
 * Quantity Measurement Application
 * =========================================================
 *
 * UC15 – Application Layer (Entry Point)
 *
 * Responsibilities:
 * - Start the application
 * - Initialize controller and service objects
 * - Accept user input
 * - Delegate operations to the controller
 *
 *   MainApp           ->     Controller.add()
 *      
 *  Controller.add()   ->     Service.add()
 *      
 *  Service.add()      ->     QuantityDAO.add() -> Quantity.add
 *
 *  LengthUnit.convertToBaseUnit() ->   Result = 2 ft
 *      
 *  Result = 2 ft                  ->   Repository.save()
 *      
 *  Repository.save()              ->   Return DTO
 *      
 *  Return DTO                     ->   Controller
 *      
 *  Controller                     ->   MainApp
 *      
 *  MainApp                        ->   Print result
 *
 * Note:
 * This class contains NO business logic.
 */

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        /*
         * =========================================================
         * Dependency Initialization
         * =========================================================
         */

        IQuantityMeasurementService service =   new QuantityMeasurementServiceImpl();

        QuantityMeasurementController controller =   new QuantityMeasurementController(service);

        Scanner scanner = new Scanner(System.in);

        System.out.println("====================================");
        System.out.println("      QUANTITY MEASUREMENT APP      ");
        System.out.println("====================================");

        /*
         * =========================================================
         * Category Selection
         * =========================================================
         */

        System.out.println("\nSelect Measurement Category:");
        System.out.println("1. Length");
        System.out.println("2. Weight");
        System.out.println("3. Volume");
        System.out.println("4. Temperature");

        System.out.print("Enter choice: ");
        int category = scanner.nextInt();
        scanner.nextLine();

        switch (category) {

        /*
         * =========================================================
         * LENGTH OPERATIONS
         * =========================================================
         */
        case 1 -> {

            System.out.println("\nAvailable Units: ft, in, yd, cm");

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

            QuantityDTO<LengthUnit> q1 = new QuantityDTO<>(v1, u1);
            QuantityDTO<LengthUnit> q2 = new QuantityDTO<>(v2, u2);

            /*
             * Delegation to Controller
             */

            System.out.println("\nEqual? " +
                    controller.compare(q1, q2));

            System.out.println("Addition: " +
                    controller.add(q1, q2));

            System.out.print("Convert first to: ");
            LengthUnit target = LengthUnit.fromString(scanner.nextLine());

            System.out.println("Converted: " +
                    controller.convert(q1, target));
        }

        /*
         * =========================================================
         * WEIGHT OPERATIONS
         * =========================================================
         */
        case 2 -> {

            System.out.println("\nAvailable Units: kg, g, lb");

            System.out.print("Enter first value: ");
            double v1 = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter first unit: ");
            WeightUnit u1 = WeightUnit.fromString(scanner.nextLine());

            System.out.print("Enter second value: ");
            double v2 = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter second unit: ");
            WeightUnit u2 = WeightUnit.fromString(scanner.nextLine());

            QuantityDTO<WeightUnit> q1 = new QuantityDTO<>(v1, u1);
            QuantityDTO<WeightUnit> q2 = new QuantityDTO<>(v2, u2);

            System.out.println("\nEqual? " +
                    controller.compare(q1, q2));

            System.out.println("Addition: " +
                    controller.add(q1, q2));

            System.out.print("Convert first to: ");
            WeightUnit target = WeightUnit.fromString(scanner.nextLine());

            System.out.println("Converted: " +
                    controller.convert(q1, target));
        }

        /*
         * =========================================================
         * VOLUME OPERATIONS
         * =========================================================
         */
        case 3 -> {

            System.out.println("\nAvailable Units: l, ml, gal");

            System.out.print("Enter first value: ");
            double v1 = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter first unit: ");
            VolumeUnit u1 = VolumeUnit.fromString(scanner.nextLine());

            System.out.print("Enter second value: ");
            double v2 = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter second unit: ");
            VolumeUnit u2 = VolumeUnit.fromString(scanner.nextLine());

            QuantityDTO<VolumeUnit> q1 = new QuantityDTO<>(v1, u1);
            QuantityDTO<VolumeUnit> q2 = new QuantityDTO<>(v2, u2);

            System.out.println("\nEqual? " +
                    controller.compare(q1, q2));

            System.out.println("Addition: " +
                    controller.add(q1, q2));

            System.out.print("Convert first to: ");
            VolumeUnit target = VolumeUnit.fromString(scanner.nextLine());

            System.out.println("Converted: " +
                    controller.convert(q1, target));
        }

        /*
         * =========================================================
         * TEMPERATURE OPERATIONS
         * =========================================================
         */
        case 4 -> {

            System.out.println("\nAvailable Units: C, F, K");

            System.out.print("Enter value: ");
            double value = scanner.nextDouble();
            scanner.nextLine();

            System.out.print("Enter unit: ");
            TemperatureUnit unit =
                    TemperatureUnit.fromString(scanner.nextLine());

            QuantityDTO<TemperatureUnit> t =
                    new QuantityDTO<>(value, unit);

            System.out.print("Convert to: ");
            TemperatureUnit target =
                    TemperatureUnit.fromString(scanner.nextLine());

            System.out.println("Converted: " +
                    controller.convert(t, target));
        }

        default -> System.out.println("Invalid option!");
        }

        scanner.close();

        System.out.println("\n====================================");
        System.out.println("        APPLICATION FINISHED        ");
        System.out.println("====================================");
    }
}