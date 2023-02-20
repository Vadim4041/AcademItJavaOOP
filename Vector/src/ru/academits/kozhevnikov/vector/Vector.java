package ru.academits.kozhevnikov.vector;

import java.util.Arrays;

public class Vector {
    private double[] numbers;

    public Vector(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException(String.format("Размер введенного вектора равен %d. Размер вектора должна быть больше нуля", length));
        }

        numbers = new double[length];
    }

    public Vector(Vector vector) {
        this.numbers = new double[vector.getSize()];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = vector.getComponent(i);
        }
    }

    public Vector(double[] numbers) {
        if (numbers.length <= 0) {
            throw new IllegalArgumentException(String.format("Размер введенного вектора равен %d. Размер вектора должна быть больше нуля", numbers.length));
        }

        this.numbers = Arrays.copyOf(numbers, numbers.length);
    }

    public Vector(int size, double[] numbers) {
        if (size <= 0) {
            throw new IllegalArgumentException(String.format("Размер введенного вектора равен %d. Размер вектора должна быть больше нуля", size));
        }

        if (numbers.length < size) {
            this.numbers = Arrays.copyOf(numbers, size);
        } else {
            this.numbers = numbers;
        }

    }

    public int getSize() {
        return numbers.length;
    }

    public double getLength() {
        return Math.abs(numbers[numbers.length - 1] - numbers[0]);
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();

        b.append("{");

        for (double number : numbers) {
            b.append(number);
            b.append(", ");
        }

        b.delete(b.lastIndexOf(", "), b.length());

        return b.append("}").toString();
    }

    public void add(Vector vector) {
        if (vector.getSize() != numbers.length) {
            double[] newArray = Arrays.copyOf(vector.numbers, Math.max(numbers.length, vector.getSize()));

            for (int i = 0; i < vector.getSize(); i++) {
                newArray[i] += vector.numbers[i];
            }

            this.numbers = newArray;
        } else {
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] += vector.getComponent(i);
            }
        }
    }

    public void subtract(Vector vector) {
        if (vector.getSize() != numbers.length) {
            double[] newArray = Arrays.copyOf(vector.numbers, Math.max(numbers.length, vector.getSize()));

            for (int i = 0; i < vector.getSize(); i++) {
                newArray[i] += vector.numbers[i];
            }

            this.numbers = newArray;
        } else {
            for (int i = 0; i < numbers.length; i++) {
                numbers[i] -= vector.getComponent(i);
            }
        }
    }

    public void multiplyByNumber(double multiplier) {
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] *= multiplier;
        }
    }

    public void reverseVector() {
        multiplyByNumber(-1);
    }

    public double getComponent(int index) {
        return numbers[index];
    }

    public void setComponent(int index, double value) {
        numbers[index] = value;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(numbers);

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        Vector vector = (Vector) obj;

        return numbers == vector.numbers;
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        vector1.add(vector2);

        return vector1;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        vector1.subtract(vector2);

        return vector1;
    }

    public static double getDotProduct(Vector vector1, Vector vector2) {
        return vector1.getLength() * vector2.getLength();
    }

}
