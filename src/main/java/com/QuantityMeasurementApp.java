package com;

import java.util.Scanner;

import com.measurement.LengthUnit;
import com.measurement.QuantityLength;
import service.EqualityService;

/**
 * UC3: Quantity Measurement Application - Uses generic QuantityLength - Takes
 * user input - Supports cross-unit equality
 */
public class QuantityMeasurementApp {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		EqualityService equalityService = new EqualityService();

		System.out.println("=== Quantity Measurement App (UC3) ===");

		try {
			// -------- First Quantity --------
			System.out.print("Enter first value: ");
			double value1 = scanner.nextDouble();

			System.out.print("Enter first unit (FEET / INCH): ");
			String unit1Input = scanner.next();

			// -------- Second Quantity --------
			System.out.print("Enter second value: ");
			double value2 = scanner.nextDouble();

			System.out.print("Enter second unit (FEET / INCH): ");
			String unit2Input = scanner.next();

			// Convert input to enum safely
			LengthUnit unit1 = LengthUnit.fromString(unit1Input);
			LengthUnit unit2 = LengthUnit.fromString(unit2Input);

			// Create Quantity objects
			QuantityLength quantity1 = new QuantityLength(value1, unit1);

			QuantityLength quantity2 = new QuantityLength(value2, unit2);

			// Equality check
			boolean result = equalityService.areEqual(quantity1, quantity2);

			// -------- Output --------
			System.out.println("\n--- Result ---");
			if (result) {
				System.out.println("The two quantities are EQUAL.");
			} else {
				System.out.println("The two quantities are NOT EQUAL.");
			}

		} catch (IllegalArgumentException ex) {
			System.out.println("Invalid unit entered. Please use FEET or INCH.");
		} finally {
			scanner.close();
		}
	}
}