package ru.academits.kozhevnikov.temperature.model.scales;

public class CelsiusScale implements Scale {
    private final double ABSOLUTE_ZERO_IN_CELSIUS = -273.15;

    @Override
    public double convertToKelvin(double temperature) {
        return temperature - ABSOLUTE_ZERO_IN_CELSIUS;
    }

    @Override
    public double convertFromKelvin(double temperature) {
        return temperature + ABSOLUTE_ZERO_IN_CELSIUS;
    }

    @Override
    public String toString() {
        return "Celsius";
    }
}
