package ru.academits.kozhevnikov.temperature.model;

import ru.academits.kozhevnikov.temperature.model.scales.Scale;

public class TemperatureConverter implements ScaleConverter {
    @Override
    public double convert(double temperature, Scale fromScale, Scale toScale) {
        return toScale.convertFromKelvin(fromScale.convertToKelvin(temperature));
    }
}
