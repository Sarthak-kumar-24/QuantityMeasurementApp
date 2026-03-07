package com.measurement;

import java.util.Objects;

/**
 * Generic class representing a length quantity with unit. * This class is
 * DRY, immutable, and unit-agnostic.
 */
public final class QuantityLength {
	private final double value;
	private final LengthUnit unit;

	public QuantityLength(double value, LengthUnit unit) {
		if (unit == null) {
			throw new IllegalArgumentException("Unit cannot be null");
		}
		this.value = value;
		this.unit = unit;
	}

	/**
	 * 
	 * Converts this quantity to base unit (inches).
	 */
	private double toBaseUnit() {
		return unit.toInches(value);
	}

	/**
	 * Value-based equality comparison.
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		// Same reference
		if (this == obj)
			return true;
		// Null or different class
		if (obj == null || getClass() != obj.getClass())
			return false;
		QuantityLength other = (QuantityLength) obj;
		return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
	}

	@Override
	public int hashCode() {
		return Objects.hash(toBaseUnit());
	}

	@Override
	public String toString() {
		return value + " " + unit;
	}
}
