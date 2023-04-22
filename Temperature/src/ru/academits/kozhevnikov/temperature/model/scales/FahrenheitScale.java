package ru.academits.kozhevnikov.temperature.model.scales;

public class FahrenheitScale implements Scale{
    @Override
    public double convertToKelvin(double temperature) {
        return new CelsiusScale().convertToKelvin((temperature - 32) / 1.8);
    }

    @Override
    public double convertFromKelvin(double temperature) {
        return new CelsiusScale().convertFromKelvin((temperature)) * 1.8 + 32;
    }

    @Override
    public String toString() {
        return "Fahrenheit";
    }
}
