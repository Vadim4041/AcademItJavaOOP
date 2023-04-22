package ru.academits.kozhevnikov.temperature.controller;

import ru.academits.kozhevnikov.temperature.model.ScaleConverter;
import ru.academits.kozhevnikov.temperature.model.TemperatureConverter;
import ru.academits.kozhevnikov.temperature.view.ConverterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller implements ActionListener {
    private final ConverterView view;
    private final ScaleConverter converter;

    public Controller(TemperatureConverter converter, ConverterView view) {
        this.converter = converter;
        this.view = view;
        this.view.setConvertButtonListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.setConvertedTemperature(Math.round(
                converter.convert(view.getTemperature(), view.getScaleFrom(), view.getScaleTo()))
        );
    }
}
