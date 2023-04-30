package ru.academits.kozhevnikov.temperature.model.scales;

public interface Scale {
    double convertToCelsius(double temperature);

    double convertFromCelsius(double temperature);
}
