package com;

import java.util.Scanner;

import com.measurement.*;
import service.EqualityService;

/*
 * ========================================================= 
 * Quantity Measurement Application
 * =========================================================
 *
 * This application allows the user to: 1. Compare two quantities (Equality) 2.
 * Add two quantities (Implicit & Explicit unit) 3. Convert a quantity from one
 * unit to another
 *
 * Supported Categories: - Length (ft, in, yd, cm) - Weight (kg, g, lb) - Volume
 * (l, ml, gal)
 *
 * Concepts Used: - Generics - Enums - OOP Principles - Clean Separation of
 * Concerns
 */
public class QuantityMeasurementApp {

	public static void main(String[] args) {

		// Scanner for user input
		Scanner scanner = new Scanner(System.in);

		// Service responsible for equality comparison
		EqualityService equalityService = new EqualityService();

		System.out.println("====================================");
		System.out.println("      QUANTITY MEASUREMENT APP       ");
		System.out.println("====================================");

		// Display category options
		System.out.println("\nSelect Measurement Category:");
		System.out.println("1. Length");
		System.out.println("2. Weight");
		System.out.println("3. Volume");
		System.out.println("4. Temperature");
		System.out.print("Enter your choice (1-4): ");

		int category = scanner.nextInt();
		scanner.nextLine(); // consume newline

		switch (category) {

		/*
		 * ================================================= LENGTH MEASUREMENT
		 * =================================================
		 */
		case 1 -> {
			System.out.println("\n--- LENGTH MEASUREMENT ---");
			System.out.println("Available Units: ft, in, yd, cm");

			// First Quantity
			System.out.print("\nEnter first value: ");
			double value1 = scanner.nextDouble();
			scanner.nextLine();
			System.out.print("Enter first unit: ");
			LengthUnit unit1 = LengthUnit.fromString(scanner.nextLine());

			// Second Quantity
			System.out.print("\nEnter second value: ");
			double value2 = scanner.nextDouble();
			scanner.nextLine();
			System.out.print("Enter second unit: ");
			LengthUnit unit2 = LengthUnit.fromString(scanner.nextLine());

			// Create Quantity objects
			Quantity<LengthUnit> q1 = new Quantity<>(value1, unit1);
			Quantity<LengthUnit> q2 = new Quantity<>(value2, unit2);

			// Equality Check
			System.out.println("\n Are quantities equal? : " + equalityService.areEqual(q1, q2));

			// Implicit Addition
			System.out.println(" Addition (default unit): " + q1.add(q2));

			// Explicit Addition
			System.out.print("\nEnter target unit for addition: ");
			LengthUnit targetUnit = LengthUnit.fromString(scanner.nextLine());
			System.out.println(" Addition (converted): " + Quantity.add(q1, q2, targetUnit));

			// Conversion
			System.out.print("\nConvert first quantity to: ");
			LengthUnit convertUnit = LengthUnit.fromString(scanner.nextLine());
			System.out.println(" Converted Value: " + q1.convertTo(convertUnit));
		}

		/*
		 * ================================================= WEIGHT MEASUREMENT
		 * =================================================
		 */
		case 2 -> {
			System.out.println("\n--- WEIGHT MEASUREMENT ---");
			System.out.println("Available Units: kg, g, lb");

			System.out.print("\nEnter first value: ");
			double value1 = scanner.nextDouble();
			scanner.nextLine();
			System.out.print("Enter first unit: ");
			WeightUnit unit1 = WeightUnit.fromString(scanner.nextLine());

			System.out.print("\nEnter second value: ");
			double value2 = scanner.nextDouble();
			scanner.nextLine();
			System.out.print("Enter second unit: ");
			WeightUnit unit2 = WeightUnit.fromString(scanner.nextLine());

			Quantity<WeightUnit> q1 = new Quantity<>(value1, unit1);
			Quantity<WeightUnit> q2 = new Quantity<>(value2, unit2);

			System.out.println("\n Are quantities equal? : " + equalityService.areEqual(q1, q2));

			System.out.println(" Addition (default unit): " + q1.add(q2));

			System.out.print("\nEnter target unit for addition: ");
			WeightUnit targetUnit = WeightUnit.fromString(scanner.nextLine());
			System.out.println(" Addition (converted): " + Quantity.add(q1, q2, targetUnit));

			System.out.print("\nConvert first quantity to: ");
			WeightUnit convertUnit = WeightUnit.fromString(scanner.nextLine());
			System.out.println(" Converted Value: " + q1.convertTo(convertUnit));
		}

		/*
		 * ================================================= VOLUME MEASUREMENT (UC11)
		 * =================================================
		 */
		case 3 -> {
			System.out.println("\n--- VOLUME MEASUREMENT ---");
			System.out.println("Available Units: l, ml, gal");

			System.out.print("\nEnter first value: ");
			double value1 = scanner.nextDouble();
			scanner.nextLine();
			System.out.print("Enter first unit: ");
			VolumeUnit unit1 = VolumeUnit.fromString(scanner.nextLine());

			System.out.print("\nEnter second value: ");
			double value2 = scanner.nextDouble();
			scanner.nextLine();
			System.out.print("Enter second unit: ");
			VolumeUnit unit2 = VolumeUnit.fromString(scanner.nextLine());

			Quantity<VolumeUnit> q1 = new Quantity<>(value1, unit1);
			Quantity<VolumeUnit> q2 = new Quantity<>(value2, unit2);

			System.out.println("\n Are quantities equal? : " + equalityService.areEqual(q1, q2));

			System.out.println(" Addition (default unit): " + q1.add(q2));

			System.out.print("\nEnter target unit for addition: ");
			VolumeUnit targetUnit = VolumeUnit.fromString(scanner.nextLine());
			System.out.println(" Addition (converted): " + Quantity.add(q1, q2, targetUnit));

			System.out.print("\nConvert first quantity to: ");
			VolumeUnit convertUnit = VolumeUnit.fromString(scanner.nextLine());
			System.out.println(" Converted Value: " + q1.convertTo(convertUnit));
		}
		case 4 -> {
			System.out.println("\n--- TEMPERATURE MEASUREMENT ---");
			System.out.println("Available Units: C, F, K");

			System.out.print("\nEnter first value: ");
			double v1 = scanner.nextDouble();
			scanner.nextLine();
			System.out.print("Enter first unit: ");
			TemperatureUnit u1 =  TemperatureUnit.fromString(scanner.nextLine());

			System.out.print("\nEnter second value: ");
			double v2 = scanner.nextDouble();
			scanner.nextLine();
			System.out.print("Enter second unit: ");
			TemperatureUnit u2 =  TemperatureUnit.fromString(scanner.nextLine());
			Quantity<TemperatureUnit> t1 = new Quantity<>(v1, u1);
			Quantity<TemperatureUnit> t2 = new Quantity<>(v2, u2);

			System.out.println("\n Are temperatures equal? : " + equalityService.areEqual(t1, t2));

			System.out.print("\nConvert first temperature to: ");
			TemperatureUnit target =  TemperatureUnit.fromString(scanner.nextLine());
			System.out.println(" Converted Value: " + t1.convertTo(target));

			// Intentionally show UC14 restriction
			try {
				t1.add(t2);
			} catch (Exception e) {
				System.out.println(" Addition blocked: " + e.getMessage());
			}
		}

		/*
		 * ================================================= INVALID OPTION
		 * =================================================
		 */
		default -> System.out.println("\n Invalid category selection!");
		}

		scanner.close();

		System.out.println("\n====================================");
		System.out.println("        THANK YOU FOR USING          ");
		System.out.println("     QUANTITY MEASUREMENT APP        ");
		System.out.println("====================================");
	}
}