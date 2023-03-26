package ru.academits.kozhevnikov.temperature.controller;


import ru.academits.kozhevnikov.temperature.model.TemperatureModel;
import ru.academits.kozhevnikov.temperature.view.TemperatureView;

import javax.swing.*;

public class TemperatureConverter extends JFrame {

    public TemperatureConverter() {
        super("Temperature Converter");

        // Create the model and view
        TemperatureModel model = new TemperatureModel();
        TemperatureView view = new TemperatureView(model);

        // Add the view to the JFrame
        getContentPane().add(view);

        setIconImage(new ImageIcon("Temperature/src/ru/academits/kozhevnikov/temperature/resources/TemperatureImage.png").getImage());

        // Set up the JFrame
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Make the JFrame visible
        setVisible(true);
    }


}
