package ru.academits.kozhevnikov.temperature_main;

import ru.academits.kozhevnikov.temperature.controller.TemperatureConverter;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        new TemperatureConverter();
    }
}
