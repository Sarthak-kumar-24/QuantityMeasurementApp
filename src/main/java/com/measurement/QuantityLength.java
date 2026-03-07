package com.measurement;

/**
 * * Generic class representing a length quantity. * Eliminates duplication from
 * UC1 and UC2.
 */
public class QuantityLength {

	private final double value;
	private final LengthUnit unit;

	public QuantityLength(double value, LengthUnit unit) {
		if (unit == null) {
			throw new IllegalArgumentException("Unit cannot be null");
		}
		this.value = value;
		this.unit = unit;
	}

	/** * Converts the quantity to base unit (inch) */
	private double toBaseUnit() {
		return unit.toBaseUnit(value);
	}

	/**
	 * * Value-based equality comparison. * Handles cross-unit equality (e.g., 1 ft
	 * == 12 inch)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		QuantityLength other = (QuantityLength) obj;
		return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
	}

	@Override
	public int hashCode() {
		return Double.hashCode(toBaseUnit());
	}
}