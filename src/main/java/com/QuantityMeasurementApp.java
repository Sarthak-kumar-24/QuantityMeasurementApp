package com;

import java.util.Scanner;

import com.measurement.Feet;

import service.EqualityService;

/**
 * QuantityMeasurementApp UC1: Feet measurement equality
 */
public class QuantityMeasurementApp {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		EqualityService equalityService = new EqualityService();

		System.out.println("=== Quantity Measurement App ===");
		/* --------------- FEET EQUALITY ---------------- */
		System.out.println("UC1: Feet Measurement Equality\n");

		// User input
		System.out.print("Enter first value in feet: ");
		double firstValue = sc.nextDouble();

		System.out.print("Enter second value in feet: ");
		double secondValue = sc.nextDouble();

		// Create Feet objects
		Feet firstFeet = new Feet(firstValue);
		Feet secondFeet = new Feet(secondValue);

		System.out.println("Result: "
				+ (equalityService.areEqual(firstFeet, secondFeet) ? "Both are Equal" : "Both are not Equal"));


		sc.close();
	}
}
