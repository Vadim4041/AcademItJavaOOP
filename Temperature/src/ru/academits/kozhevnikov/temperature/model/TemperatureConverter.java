package ru.academits.kozhevnikov.temperature.model;

import ru.academits.kozhevnikov.temperature.model.scales.Scale;

import java.util.List;

public record TemperatureConverter(List<Scale> scales) implements ScaleConverter {
    @Override
    public double convert(double temperature, Scale fromScale, Scale toScale) {
        return toScale.convertFromCelsius(fromScale.convertToCelsius(temperature));
    }
}
