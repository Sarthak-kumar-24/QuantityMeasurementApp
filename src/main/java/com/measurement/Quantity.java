package com.measurement;

import java.util.Objects;
import java.util.function.DoubleBinaryOperator;

public final class Quantity<U extends IMeasurable> extends Measurement {

    private final U unit;

    /* ===============================
       Arithmetic Operation Enum
       =============================== */
    private enum ArithmeticOperation {
        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> {
            if (b == 0.0) {
                throw new ArithmeticException("Divide by zero");
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

    /* ===============================
       CENTRALIZED VALIDATION
       =============================== */
    private void validateOperands(Quantity<U> other, boolean targetRequired, U targetUnit) {
        if (other == null) {
            throw new IllegalArgumentException("Operand cannot be null");
        }
        if (!unit.getClass().equals(other.unit.getClass())) {
            throw new IllegalArgumentException("Cross-category operation not allowed");
        }
        if (!Double.isFinite(other.value)) {
            throw new IllegalArgumentException("Operand value must be finite");
        }
        if (targetRequired && targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
    }

    /* ===============================
       CENTRALIZED ARITHMETIC
       =============================== */
    private double performBaseArithmetic(
            Quantity<U> other,
            ArithmeticOperation operation
    ) {
        double baseA = unit.convertToBaseUnit(value);
        double baseB = other.unit.convertToBaseUnit(other.value);
        return operation.compute(baseA, baseB);
    }

    private double roundTwoDecimals(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

    /* ===============================
       ADDITION
       =============================== */
    public Quantity<U> add(Quantity<U> other) {
        return add(other, unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validateOperands(other, true, targetUnit);
        double baseResult = performBaseArithmetic(other, ArithmeticOperation.ADD);
        double result = targetUnit.convertFromBaseUnit(baseResult);
        return new Quantity<>(roundTwoDecimals(result), targetUnit);
    }

    /* ===============================
       SUBTRACTION
       =============================== */
    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        validateOperands(other, true, targetUnit);
        double baseResult = performBaseArithmetic(other, ArithmeticOperation.SUBTRACT);
        double result = targetUnit.convertFromBaseUnit(baseResult);
        return new Quantity<>(roundTwoDecimals(result), targetUnit);
    }

    /* ===============================
       DIVISION
       =============================== */
    public double divide(Quantity<U> other) {
        validateOperands(other, false, null);
        return performBaseArithmetic(other, ArithmeticOperation.DIVIDE);
    }

    /* ===============================
       CONVERSION
       =============================== */
    public Quantity<U> convertTo(U targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double base = unit.convertToBaseUnit(value);
        double converted = targetUnit.convertFromBaseUnit(base);
        return new Quantity<>(converted, targetUnit);
    }

    /* ===============================
       STATIC ADD
       =============================== */
    public static <U extends IMeasurable>
    Quantity<U> add(Quantity<U> a, Quantity<U> b, U targetUnit) {
        return a.add(b, targetUnit);
    }

    /* ===============================
       EQUALITY
       =============================== */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quantity<?> other)) return false;
        if (!unit.getClass().equals(other.unit.getClass())) return false;

        double a = unit.convertToBaseUnit(value);
        double b = ((IMeasurable) other.unit).convertToBaseUnit(other.value);

        return Math.abs(a - b) < 1e-6;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unit.getClass(),
                Math.round(unit.convertToBaseUnit(value) * 1e6));
    }

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit.getUnitName() + ")";
    }
}