package ru.academits.kozhevnikov.temperature.model;

import ru.academits.kozhevnikov.temperature.model.scales.CelsiusScale;
import ru.academits.kozhevnikov.temperature.model.scales.FahrenheitScale;
import ru.academits.kozhevnikov.temperature.model.scales.KelvinScale;
import ru.academits.kozhevnikov.temperature.model.scales.Scale;

import java.util.List;

public class TemperatureConverter implements ScaleConverter {
    List<Scale> scales = List.of(
            new CelsiusScale(),
            new KelvinScale(),
            new FahrenheitScale()
    );

    @Override
    public double convert(double temperature, Scale fromScale, Scale toScale) {
        return toScale.convertFromCelsius(fromScale.convertToCelsius(temperature));
    }

    public List<Scale> getScales() {
        return scales;
    }
}
