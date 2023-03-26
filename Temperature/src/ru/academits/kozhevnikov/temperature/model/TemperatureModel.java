package ru.academits.kozhevnikov.temperature.model;

public class TemperatureModel {
    private double inputTemp;
    private double outputTemp;
    private String fromScale;
    private String toScale;

    public void setInputTemp(double temp) {
        inputTemp = temp;
    }

    public void setFromScale(String scale) {
        fromScale = scale;
    }

    public void setToScale(String scale) {
        toScale = scale;
    }

    public double getOutputTemp() {
        return outputTemp;
    }

    public void convert() {
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
    }
}
