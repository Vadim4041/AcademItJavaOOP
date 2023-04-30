package ru.academits.kozhevnikov.temperature.model.scales;

public class FahrenheitScale implements Scale {
    @Override
    public double convertToCelsius(double temperature) {
        return (temperature - 32) / 1.8;
    }

    @Override
    public double convertFromCelsius(double temperature) {
        return temperature * 1.8 + 32;
    }

    @Override
    public String toString() {
        return "Fahrenheit";
    }
}
