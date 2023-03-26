package ru.academits.kozhevnikov.temperature;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TemperatureConverter extends JFrame {
    private final JTextField inputField;
    private final JTextField outputField;
    private final JComboBox<String> conversionDirection;

    public TemperatureConverter() {
        super("Temperature Converter");

        // Set up the JFrame
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the input label and text field
        JLabel inputLabel = new JLabel("Input temperature:");
        inputField = new JTextField(10);

        // Create the output label and text field
        JLabel outputLabel = new JLabel("Converted temperature:");
        outputField = new JTextField(10);
        outputField.setEditable(false);

        // Create the conversion direction combo box
        conversionDirection = new JComboBox<>(new String[]{"Celsius to Fahrenheit", "Celsius to Kelvin",
                "Fahrenheit to Celsius", "Fahrenheit to Kelvin", "Kelvin to Celsius", "Kelvin to Fahrenheit"});

        // Create the Convert button
        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(new ConvertButtonListener());

        // Add the components to the JFrame
        setLayout(new GridLayout(4, 2));
        add(inputLabel);
        add(inputField);
        add(conversionDirection);
        add(convertButton);
        add(outputLabel);
        add(outputField);
        add(new JLabel(""));
        add(new JLabel(""));

        // Make the JFrame visible
        setVisible(true);
    }

    private class ConvertButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                // Get the input temperature
                double inputTemp = Double.parseDouble(inputField.getText());

                // Determine the conversion direction
                String direction = (String) conversionDirection.getSelectedItem();
                assert direction != null;
                String[] parts = direction.split(" to ");
                String fromScale = parts[0];
                String toScale = parts[1];

                // Convert the temperature
                double outputTemp;
                if (fromScale.equals("Celsius") && toScale.equals("Fahrenheit")) {
                    outputTemp = (inputTemp * 9 / 5) + 32;
                } else if (fromScale.equals("Celsius") && toScale.equals("Kelvin")) {
                    outputTemp = inputTemp + 273.15;
                } else if (fromScale.equals("Fahrenheit") && toScale.equals("Celsius")) {
                    outputTemp = (inputTemp - 32) * 5 / 9;
                } else if (fromScale.equals("Fahrenheit") && toScale.equals("Kelvin")) {
                    outputTemp = (inputTemp + 459.67) * 5 / 9;
                } else if (fromScale.equals("Kelvin") && toScale.equals("Celsius")) {
                    outputTemp = inputTemp - 273.15;
                } else {
                    outputTemp = (inputTemp * 9 / 5) - 459.67;
                }

                // Set the converted temperature
                outputField.setText(String.format("%.2f", outputTemp));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(TemperatureConverter.this, "Please enter a valid temperature.");
            }
        }
    }

    public static void main(String[] args) {
        new TemperatureConverter();
    }
}
