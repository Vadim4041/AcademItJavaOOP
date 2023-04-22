package ru.academits.kozhevnikov.temperature_main;

import ru.academits.kozhevnikov.temperature.controller.Controller;
import ru.academits.kozhevnikov.temperature.model.TemperatureConverter;
import ru.academits.kozhevnikov.temperature.model.scales.CelsiusScale;
import ru.academits.kozhevnikov.temperature.model.scales.FahrenheitScale;
import ru.academits.kozhevnikov.temperature.model.scales.KelvinScale;
import ru.academits.kozhevnikov.temperature.model.scales.Scale;
import ru.academits.kozhevnikov.temperature.view.ConverterView;
import ru.academits.kozhevnikov.temperature.view.SwingConverterView;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Scale> scales = new ArrayList<>(Arrays.asList(
                new KelvinScale(),
                new CelsiusScale(),
                new FahrenheitScale()
        ));

        TemperatureConverter converter = new TemperatureConverter();
        ConverterView view = new SwingConverterView(scales);
        new Controller(converter, view);
    }
}
