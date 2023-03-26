package ru.academits.kozhevnikov.temperature.model.scales;

public class FahrenheitScale implements Scale {
    @Override
    public double convertToKelvin(double temperature) {
        return 0;
    }

    @Override
    public double convertFromKelvin(double temperature) {
        return 0;
    }

    @Override
    public String toString() {
        return "Fahrenheit";
    }
}
