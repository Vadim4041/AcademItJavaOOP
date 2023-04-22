package ru.academits.kozhevnikov.temperature.model.scales;

public interface Scale {
    double convertToKelvin(double temperature);
    double convertFromKelvin(double temperature);
}
