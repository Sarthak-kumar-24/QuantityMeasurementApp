package com.measurement;

import java.util.Objects;
import java.util.function.DoubleBinaryOperator;

/*
 * ========================================================= 
 * Generic Immutable Quantity Class 
 * =========================================================
 *
 * UC10 : Generic Quantity using Generics 
 * UC11 : Supports Volume (via units)
 * UC12 : Arithmetic operations 
 * UC13 : Centralized arithmetic handling 
 * UC14 : Strict validation & operation control
 *
 * Design Highlights: 
 * - Immutable class (thread-safe) 
 * - Strong validation 
 * - No cross-category arithmetic allowed 
 * - Unit conversion handled via base units
 */
public final class Quantity<U extends IMeasurable> extends Measurement {

	/** Unit associated with the quantity */
	private final U unit;

	/*
	 * ================================================= 
	 * UC13 – Centralized Arithmetic Strategy 
	 * =================================================
	 */
	private enum ArithmeticOperation {

		ADD((a, b) -> a + b), SUBTRACT((a, b) -> a - b), DIVIDE((a, b) -> {
			if (b == 0.0) {
				throw new ArithmeticException("Divide by zero is not allowed");
			}
			return a / b;
		});

		private final DoubleBinaryOperator operator;

		ArithmeticOperation(DoubleBinaryOperator operator) {
			this.operator = operator;
		}

		double compute(double a, double b) {
			return operator.applyAsDouble(a, b);
		}
	}

	/*
	 * ================================================= 
	 * Constructor
	 * =================================================
	 */
	public Quantity(double value, U unit) {
		super(value);

		if (unit == null) {
			throw new IllegalArgumentException("Unit cannot be null");
		}
		this.unit = unit;
	}

	/*
	 * ================================================= 
	 * Getter
	 * =================================================
	 */
	public U getUnit() {
		return unit;
	}

	/*
	 * ================================================= 
	 * UC13 + UC14 – Operand Validation 
	 * =================================================
	 */
	private void validateOperands(Quantity<U> other, ArithmeticOperation operation, U targetUnit) {

		if (other == null) {
			throw new IllegalArgumentException("Operand quantity cannot be null");
		}

		// Prevent cross-category operations (Length + Weight )
		if (!unit.getClass().equals(other.unit.getClass())) {
			throw new IllegalArgumentException("Cross-category operation not allowed");
		}

		// Ensure operand value is valid
		if (!Double.isFinite(other.value)) {
			throw new IllegalArgumentException("Operand value must be finite");
		}

		// For ADD & SUBTRACT, target unit is mandatory
		if (operation != ArithmeticOperation.DIVIDE && targetUnit == null) {
			throw new IllegalArgumentException("Target unit cannot be null");
		}

		// UC14: Let unit decide which operations it supports
		unit.validateOperationSupport(operation.name());
	}

	/*
	 * ================================================= 
	 * Base Unit Arithmetic
	 * =================================================
	 */
	private double performBaseArithmetic(Quantity<U> other, ArithmeticOperation operation) {

		// Convert both quantities to base unit
		double a = unit.convertToBaseUnit(value);
		double b = other.unit.convertToBaseUnit(other.value);

		return operation.compute(a, b);
	}

	/*
	 * ================================================= 
	 * Utility: Rounding (2 decimal precision) 
	 * =================================================
	 */
	private double round(double value) {
		return Math.round(value * 100.0) / 100.0;
	}

	/*
	 * ================================================= 
	 * ADDITION
	 * =================================================
	 */
	public Quantity<U> add(Quantity<U> other) {
		return add(other, unit);
	}

	public Quantity<U> add(Quantity<U> other, U targetUnit) {
		validateOperands(other, ArithmeticOperation.ADD, targetUnit);

		double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);

		return new Quantity<>(round(targetUnit.convertFromBaseUnit(baseResult)), targetUnit);
	}

	/*
	 * ================================================= 
	 * SUBTRACTION
	 * =================================================
	 */
	public Quantity<U> subtract(Quantity<U> other) {
		return subtract(other, unit);
	}

	public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
		validateOperands(other, ArithmeticOperation.SUBTRACT, targetUnit);

		double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);

		return new Quantity<>(round(targetUnit.convertFromBaseUnit(baseResult)), targetUnit);
	}

	/*
	 * ================================================= 
	 * DIVISION (Dimensionless Result) 
	 * =================================================
	 */
	public double divide(Quantity<U> other) {
		validateOperands(other, ArithmeticOperation.DIVIDE, null);
		return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
	}

	/*
	 * ================================================= 
	 * UNIT CONVERSION
	 * =================================================
	 */
	public Quantity<U> convertTo(U targetUnit) {

		if (targetUnit == null) {
			throw new IllegalArgumentException("Target unit cannot be null");
		}

		double baseValue = unit.convertToBaseUnit(value);

		return new Quantity<>(targetUnit.convertFromBaseUnit(baseValue), targetUnit);
	}

	/*
	 * ================================================= 
	 * Static Utility Method(Explicit Addition) 
	 * =================================================
	 */
	public static <U extends IMeasurable> Quantity<U> add(Quantity<U> a, Quantity<U> b, U targetUnit) {
		return a.add(b, targetUnit);
	}

	/*
	 * ================================================= 
	 * EQUALITY (Base Unit Comparison) 
	 * =================================================
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (!(obj instanceof Quantity<?> other))
			return false;

		if (!unit.getClass().equals(other.unit.getClass()))
			return false;

		double a = unit.convertToBaseUnit(value);
		double b = ((IMeasurable) other.unit).convertToBaseUnit(other.value);

		// Tolerance-based comparison
		return Math.abs(a - b) < 1e-6;
	}

	@Override
	public int hashCode() {
		return Objects.hash(unit.getClass(), Math.round(unit.convertToBaseUnit(value) * 1e6));
	}

	/*
	 * ================================================= 
	 * STRING REPRESENTATION
	 * =================================================
	 */
	@Override
	public String toString() {
		return "Quantity(" + value + ", " + unit.getUnitName() + ")";
	}
}