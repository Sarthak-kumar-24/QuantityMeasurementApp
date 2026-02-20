package com;

import java.util.Scanner;

import com.measurement.Feet;
import com.measurement.Inches;

import service.EqualityService;

/**
 * QuantityMeasurementApp UC1: Feet measurement equality
 */
public class QuantityMeasurementApp {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		EqualityService equalityService = new EqualityService();

		System.out.println("=== Quantity Measurement App ===");
		/* --------------- FEET EQUALITY ---------------- */
		System.out.println("UC1: Feet Measurement Equality\n");

		// User input
		System.out.print("Enter first value in feet: ");
		double firstValue = scanner.nextDouble();

		System.out.print("Enter second value in feet: ");
		double secondValue = scanner.nextDouble();

		// Create Feet objects
		Feet firstFeet = new Feet(firstValue);
		Feet secondFeet = new Feet(secondValue);

		System.out.println("Result: "
				+ (equalityService.areEqual(firstFeet, secondFeet) ? "Both are Equal" : "Both are not Equal"));

		/* -------- INCHES EQUALITY (UC2) -------- */
		System.out.println("\nUC2: Inches Measurement Equality");

		System.out.print("Enter first value in inches: ");
		double inch1 = scanner.nextDouble();

		System.out.print("Enter second value in inches: ");
		double inch2 = scanner.nextDouble();

		Inches firstInch = new Inches(inch1);
		Inches secondInch = new Inches(inch2);

		System.out.println("Result: "
				+ (equalityService.areEqual(firstInch, secondInch) ? "Both are Equal" : "Both are not Equal"));

		scanner.close();
	}
}
