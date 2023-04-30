package ru.academits.kozhevnikov.temperature.model.scales;

public class KelvinScale implements Scale {
    private static final double ABSOLUTE_ZERO_IN_CELSIUS = -273.15;

    @Override
    public double convertToCelsius(double temperature) {
        return temperature + ABSOLUTE_ZERO_IN_CELSIUS;
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return temperature - ABSOLUTE_ZERO_IN_CELSIUS;
    }

    @Override
    public String toString() {
        return "Kelvin";
    }
}
