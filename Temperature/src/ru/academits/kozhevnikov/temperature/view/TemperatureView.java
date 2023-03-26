package ru.academits.kozhevnikov.temperature.view;

import ru.academits.kozhevnikov.temperature.model.TemperatureModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TemperatureView extends JPanel implements ActionListener {
    private final TemperatureModel model;
    private final JTextField inputField;
    private final JTextField outputField;
    private final JComboBox<String> conversionDirection;
    private final JButton convertButton;

    public TemperatureView(TemperatureModel m) {
        model = m;

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
        conversionDirection.addActionListener(this);

        // Create the Convert button
        convertButton = new JButton("Convert");
        convertButton.addActionListener(this);

        // Add the components to the panel
        setLayout(new GridLayout(4, 2));
        add(inputLabel);
        add(inputField);
        add(outputLabel);
        add(outputField);
        add(new JLabel("Conversion direction:"));
        add(conversionDirection);
        add(new JLabel());
        add(convertButton);

    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == convertButton) {
            // Get the input temperature from the input text field
            String inputText = inputField.getText();
            double inputTemp;
            try {
                inputTemp = Double.parseDouble(inputText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input temperature");
                return;
            }

            // Determine the conversion direction from the combo box
            String direction = (String) conversionDirection.getSelectedItem();
            assert direction != null;
            String[] scales = direction.split(" to ");
            String fromScale = scales[0];
            String toScale = scales[1];

            // Set the input temperature and conversion direction in the model
            model.setInputTemp(inputTemp);
            model.setFromScale(fromScale);
            model.setToScale(toScale);

            // Convert the temperature
            model.convert();

            // Get the output temperature from the model and display it in the output text field
            double outputTemp = model.getOutputTemp();
            outputField.setText(String.format("%.2f", outputTemp));
        }
    }
}
