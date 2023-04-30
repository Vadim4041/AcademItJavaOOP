package ru.academits.kozhevnikov.temperature_main;

import ru.academits.kozhevnikov.temperature.controller.Controller;
import ru.academits.kozhevnikov.temperature.model.TemperatureConverter;
import ru.academits.kozhevnikov.temperature.view.ConverterView;
import ru.academits.kozhevnikov.temperature.view.SwingConverterView;

public class Main {
    public static void main(String[] args) {
        TemperatureConverter converter = new TemperatureConverter();
        ConverterView view = new SwingConverterView(converter.getScales());
        new Controller(converter, view);
    }
}
