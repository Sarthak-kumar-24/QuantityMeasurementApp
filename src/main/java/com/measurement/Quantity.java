package com.measurement;

import java.util.Objects;

/**
 * UC10: Generic Quantity class
 */
public final class Quantity<U extends IMeasurable> extends Measurement {

	private static final double EPSILON = 1e-6;
	private final U unit;

	public Quantity(double value, U unit) {
		super(value);
		if (unit == null) {
			throw new IllegalArgumentException("Unit cannot be null");
		}
		this.unit = unit;
	}

	public U getUnit() {
		return unit;
	}

	/* Conversion */
	public Quantity<U> convertTo(U target) {
		if (target == null) {
			throw new IllegalArgumentException("Target unit cannot be null");
		}
		double base = unit.convertToBaseUnit(value);
		return new Quantity<>(target.convertFromBaseUnit(base), target);
	}

	/* Addition (implicit target) */
	public Quantity<U> add(Quantity<U> other) {
		return add(this, other, this.unit);
	}

	/* Addition (explicit target) */
	public static <U extends IMeasurable> Quantity<U> add(Quantity<U> q1, Quantity<U> q2, U target) {
		validateSameCategory(q1, q2, target);

		double baseSum = q1.unit.convertToBaseUnit(q1.value) + q2.unit.convertToBaseUnit(q2.value);

		return new Quantity<>(target.convertFromBaseUnit(baseSum), target);
	}
	/* ===================== Subtraction (UC12) ===================== */

	public Quantity<U> subtract(Quantity<U> other) {
		return subtract(this, other, this.unit);
	}

	public static <U extends IMeasurable> Quantity<U> subtract(Quantity<U> q1, Quantity<U> q2, U target) {
		validateSameCategory(q1, q2, target);

		double baseResult = q1.unit.convertToBaseUnit(q1.value) - q2.unit.convertToBaseUnit(q2.value);

		return new Quantity<>(target.convertFromBaseUnit(baseResult), target);
	}

	/* ===================== Division (UC12) ===================== */

	public double divide(Quantity<U> other) {
		if (other == null) {
			throw new IllegalArgumentException("Other quantity cannot be null");
		}
		if (!unit.getClass().equals(other.unit.getClass())) {
			throw new IllegalArgumentException("Cross-category division not allowed");
		}

		double divisor = other.unit.convertToBaseUnit(other.value);
		if (Math.abs(divisor) < EPSILON) {
			throw new ArithmeticException("Division by zero");
		}

		double dividend = unit.convertToBaseUnit(value);
		return dividend / divisor;
	}

	/* ===================== Equality ===================== */

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Quantity<?> other))
			return false;
		if (!unit.getClass().equals(other.unit.getClass()))
			return false;

		double b1 = unit.convertToBaseUnit(value);
		double b2 = ((IMeasurable) other.unit).convertToBaseUnit(other.value);

		return Math.abs(b1 - b2) < EPSILON;
	}

	@Override
	public int hashCode() {
		return Objects.hash(unit.getClass(), Math.round(unit.convertToBaseUnit(value) / EPSILON));
	}

	@Override
	public String toString() {
		return "Quantity(" + value + ", " + unit.getUnitName() + ")";
	}
	/* ===================== Helpers ===================== */

	private static <U extends IMeasurable> void validateSameCategory(Quantity<U> q1, Quantity<U> q2, U target) {
		if (q1 == null || q2 == null) {
			throw new IllegalArgumentException("Operands cannot be null");
		}
		if (target == null) {
			throw new IllegalArgumentException("Target unit cannot be null");
		}
		if (!q1.unit.getClass().equals(q2.unit.getClass()) || !q1.unit.getClass().equals(target.getClass())) {
			throw new IllegalArgumentException("Cross-category operation not allowed");
		}
	}
}