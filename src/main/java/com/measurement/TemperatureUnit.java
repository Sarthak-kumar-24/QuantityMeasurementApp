package com.measurement;


public enum TemperatureUnit implements IMeasurable {

    CELSIUS("C"),
    FAHRENHEIT("F"),
    KELVIN("K");

    private final String symbol;

    TemperatureUnit(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public double getConversionFactor() {
        return 1.0; // not used
    }

 

    @Override
    public boolean supportsArithmetic() {
        return false; // UC14 default restriction
    }
    
    public static TemperatureUnit fromString(String input) {
        return switch (input.trim().toUpperCase()) {
            case "C", "CELSIUS" -> CELSIUS;
            case "F", "FAHRENHEIT" -> FAHRENHEIT;
            case "K", "KELVIN" -> KELVIN;
            default -> throw new IllegalArgumentException(
                "Invalid temperature unit. Use C, F, or K"
            );
        };
    }

    @Override
    public String getUnitName() {
        return symbol;
    }


    @Override
    public double convertToBaseUnit(double value) {
        // Base = Celsius
        return switch (this) {
            case CELSIUS -> value;
            case FAHRENHEIT -> (value - 32) * 5 / 9;
            case KELVIN -> value - 273.15;
        };
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return switch (this) {
            case CELSIUS -> baseValue;
            case FAHRENHEIT -> baseValue * 9 / 5 + 32;
            case KELVIN -> baseValue + 273.15;
        };
    }

    @Override
    public void validateOperationSupport(String operation) {
        throw new UnsupportedOperationException(
            "Temperature does not support arithmetic operation: " + operation
        );
    }
}
