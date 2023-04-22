package ru.academits.kozhevnikov.temperature.view;

import ru.academits.kozhevnikov.temperature.model.scales.Scale;

import java.awt.event.ActionListener;

public interface ConverterView {
    double getTemperature();

    void setConvertedTemperature(double convertedTemperature);

    Scale getScaleFrom();

    Scale getScaleTo();

    void setConvertButtonListener(ActionListener actionListener);
}
