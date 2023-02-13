package ru.academits.kozhevnikov.vector;

import java.util.Arrays;

public class Vector {
    private final int length;

    private double[] array;

    public Vector(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Длина должна быть больше нуля");
        }

        this.length = length;
        array = new double[length];
    }

    public Vector(Vector vector) {
        length = vector.length;
        array = vector.array;
    }

    public Vector(double[] array) {
        this.array = array;
        length = array.length;
    }

    public Vector(int length, double[] array1) {
        if (length <= 0) {
            throw new IllegalArgumentException("Длина должна быть больше нуля");
        }

        this.length = length;

        if (array1.length < length) {
            array = new double[length];

            System.arraycopy(array1, 0, array, 0, array1.length);
        } else {
            this.array = array1;
        }
    }

    public int getSize() {
        return length;
    }

    @Override
    public String toString() {
        int iMax = length - 1;

        StringBuilder b = new StringBuilder();

        b.append("{");

        for (int i = 0; ; i++) {
            b.append(array[i]);

            if (i == iMax) {
                return b.append("}").toString();
            }

            b.append(", ");
        }
    }

    public void addVector(Vector vector) {
        double[] newArray = new double[Math.max(length, vector.length)];

        if (length >= 0) {
            System.arraycopy(array, 0, newArray, 0, newArray.length);
        }

        for (int i = 0; i < newArray.length; i++) {
            newArray[i] += vector.array[i];
        }

        array = newArray;
    }

    public void subtractVector(Vector vector) {
        double[] newArray = new double[Math.max(length, vector.length)];

        if (length >= 0) {
            System.arraycopy(array, 0, newArray, 0, newArray.length);
        }

        for (int i = 0; i < newArray.length; i++) {
            newArray[i] -= vector.array[i];
        }

        array = newArray;
    }

    public void multiplyVector(double multiplier) {
        double[] newArray = new double[length];

        System.arraycopy(array, 0, newArray, 0, length);

        for (int i = 0; i < length; i++) {
            newArray[i] *= multiplier;
        }

        array = newArray;
    }

    public void reverseVector() {
        double[] newArray = new double[length];

        System.arraycopy(array, 0, newArray, 0, length);

        for (int i = 0; i < length; i++) {
            newArray[i] *= -1;
        }

        array = newArray;
    }

    public double getComponent(int index) {
        return array[index];
    }

    public void setComponent(int index, double value) {
        array[index] = value;
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = prime * hash + length + Arrays.hashCode(array);

        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Vector)) {
            return false;
        }

        return (obj == this) || (length == ((Vector) obj).length && array == ((Vector) obj).array);
    }

    public static Vector sumVectorsStatic(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(Math.max(vector1.length, vector2.length));

        if (vector1.length >= 0) {
            System.arraycopy(vector1.array, 0, resultVector.array, 0, vector1.length);
        }

        for (int i = 0; i < vector2.length; i++) {
            resultVector.array[i] += vector2.array[i];
        }

        return resultVector;
    }

    public static Vector subtractVectorsStatic(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(Math.max(vector1.length, vector2.length));

        if (vector1.length >= 0) {
            System.arraycopy(vector1.array, 0, resultVector.array, 0, vector1.length);
        }

        for (int i = 0; i < vector2.length; i++) {
            resultVector.array[i] -= vector2.array[i];
        }

        return resultVector;
    }

    public static Vector multiplyVectorsStatic(Vector vector1, Vector vector2) {
        Vector resultVector = new Vector(Math.max(vector1.length, vector2.length));

        if (vector1.length >= 0) {
            System.arraycopy(vector1.array, 0, resultVector.array, 0, vector1.length);
        }

        for (int i = 0; i < vector2.length; i++) {
            resultVector.array[i] *= vector2.array[i];
        }

        return resultVector;
    }

}
