package ru.academits.kozhevnikov.temperature.model.scales;

public class KelvinScale implements Scale{
    @Override
    public double convertToKelvin(double temperature) {
        return temperature;
    }

    @Override
    public double convertFromKelvin(double temperature) {
        return temperature;
    }

    @Override
    public String toString() {
        return "Kelvin";
    }
}
