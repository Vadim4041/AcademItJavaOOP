package ru.academits.kozhevnikov.temperature.model;

import ru.academits.kozhevnikov.temperature.model.scales.Scale;

public interface ScaleConverter {
    double convert(double temperature, Scale fromScale, Scale toScale);
}
