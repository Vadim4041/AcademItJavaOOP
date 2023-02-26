package ru.academits.kozhevnikov.vector;

import java.util.Arrays;

public class Vector {
    private double[] components;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException(String.format("Введенный размер вектора равен %d. Размер вектора должен быть больше нуля", size));
        }

        components = new double[size];
    }

    public Vector(Vector vector) {
        this.components = new double[vector.components.length];

        System.arraycopy(vector.components, 0, components, 0, components.length);
    }

    public Vector(double[] components) {
        if (components.length <= 0) {
            throw new IllegalArgumentException(String.format("Размер переданного массива вещественных чисел равен %d. Размер вектора должен быть больше нуля", components.length));
        }

        this.components = Arrays.copyOf(components, components.length);
    }

    public Vector(int size, double[] numbers) {
        if (size <= 0) {
            throw new IllegalArgumentException(String.format("Введенный размер вектора равен %d. Размер вектора должна быть больше нуля", size));
        }

        if (numbers.length <= 0) {
            throw new IllegalArgumentException(String.format("Размер переданного массива вещественных чисел равен %d. Размер вектора должен быть больше нуля", numbers.length));
        }

        this.components = Arrays.copyOf(numbers, size);
    }

    public int getSize() {
        return components.length;
    }

    public double getLength() {
        double squaredComponentsSum = 0;

        for (double e : components) {
            squaredComponentsSum += Math.pow(e, 2);
        }

        return Math.sqrt(squaredComponentsSum);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{");

        for (double number : components) {
            sb.append(number);
            sb.append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());

        return sb.append("}").toString();
    }

    public void add(Vector vector) {
        if (vector.components.length > components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] += vector.components[i];
        }
    }

    public void subtract(Vector vector) {
        if (vector.components.length > components.length) {
            components = Arrays.copyOf(components, vector.components.length);
        }

        for (int i = 0; i < vector.components.length; i++) {
            components[i] -= vector.components[i];
        }
    }

    public void multiplyByNumber(double multiplier) {
        for (int i = 0; i < components.length; i++) {
            components[i] *= multiplier;
        }
    }

    public void reverse() {
        multiplyByNumber(-1);
    }

    public double getComponent(int index) {
        return components[index];
    }

    public void setComponent(int index, double value) {
        components[index] = value;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + Arrays.hashCode(components);

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

        return Arrays.equals(components, vector.components);
    }

    public static Vector getSum(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);
        result.add(vector2);

        return result;
    }

    public static Vector getDifference(Vector vector1, Vector vector2) {
        Vector result = new Vector(vector1);
        result.subtract(vector2);

        return result;
    }

    public static double getDotProduct(Vector vector1, Vector vector2) {
        double result = 0;

        for (int i = 0; i < Math.min(vector1.components.length, vector2.components.length); i++) {
            result += vector1.components[i] * vector2.components[i];
        }

        return result;
    }
}
