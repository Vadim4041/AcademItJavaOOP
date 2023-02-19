package ru.academits.kozhevnikov.matrix;

import ru.academits.kozhevnikov.matrix_main.TestGPT;
import ru.academits.kozhevnikov.vector.Vector;

public class Matrix {
    private final int size;

    private final Vector[] vectors; // было double[][]

    public Matrix(int rows, int columns) {
        if (columns <= 0 || rows <= 0) {
            throw new IllegalArgumentException("Размер матрицы должен быть больше нуля");
        }

        size = Math.max(rows, columns);
        vectors = new Vector[size];

        for (int i = 0; i < vectors.length; i++) {
            vectors[i] = new Vector(size);
        }
    }

    public Matrix(Matrix matrix) {
        size = matrix.size;
        vectors = matrix.vectors;
    }

    public Matrix(double[][] array) {
        int maxColumns = array[0].length;

        for (double[] e : array) {
            if (maxColumns < e.length) {
                maxColumns = e.length;
            }
        }

        size = Math.max(array.length, maxColumns);

        vectors = new Vector[size];

        for (int i = 0; i < array.length; i++) {
            vectors[i] = new Vector(size);

            for (int j = 0; j < array[i].length; j++) {
                vectors[i].setComponent(j, array[i][j]);
            }
        }
    }

    public Matrix(Vector[] vector) {
        int maxColumns = vector[0].getSize();

        for (Vector e : vector) {
            if (maxColumns < e.getSize()) {
                maxColumns = e.getSize();
            }
        }

        size = Math.max(vector.length, maxColumns);
        vectors = new Vector[size];

        for (int i = 0; i < vector.length; i++) {
            vectors[i] = new Vector(size);

            for (int j = 0; j < vector[i].getSize(); j++) {
                vectors[i].setComponent(j, vector[i].getComponent(j));
            }
        }
    }

    @Override
    public String toString() {
        int jMax = vectors[0].getSize() - 1;
        int iMax = vectors.length - 1;

        StringBuilder b = new StringBuilder();

        b.append("{");

        for (int i = 0; ; i++) {
            b.append("{");

            for (int j = 0; ; j++) {
                b.append(vectors[i].getComponent(j));

                if (j == jMax) {
                    b.append("}");
                    break;
                }

                b.append(", ");
            }

            if (i == iMax) {
                return b.append("}").toString();
            }

            b.append(", ");
        }
    }

    public int getSize() {
        return size;
    }

    public Vector getVectorRow(int row) {
        return vectors[row];
    }

    public void setVectorRow(int index, Vector vector) {
        vectors[index] = vector;
    }

    public Vector getVectorColumn(int index) {
        Vector vector = new Vector(vectors.length);

        for (int i = 0; i < vector.getSize(); i++) {
            vector.setComponent(i, vectors[i].getComponent(index));
        }

        return vector;
    }

    public void transposeMatrix() {
        Vector[] vectorsCopy = new Vector[size];

        for (int i = 0; i < vectorsCopy.length; i++) {
            vectorsCopy[i] = new Vector(size);

            for (int j = 0; j < size; j++) {
                vectorsCopy[i].setComponent(j, vectors[i].getComponent(j));
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                vectors[i].setComponent(j, vectorsCopy[j].getComponent(i));
            }
        }
    }

    public void multiplyMatrixByNumber(double multiplier) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                vectors[i].setComponent(j, vectors[i].getComponent(j) * multiplier);
            }
        }
    }

    public double getDeterminant() {
        if (size == 1) {
            return vectors[0].getComponent(0);
        }

        if (size == 2) {
            return vectors[0].getComponent(0) * vectors[1].getComponent(1) - vectors[0].getComponent(1) * vectors[1].getComponent(0);
        }

        double result = 0;

        for (int i = 0; i < size; i++) {
            double[][] subMatrix = new double[size - 1][size - 1];

            for (int j = 1; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    if (k < i) {
                        subMatrix[j - 1][k] = vectors[j].getComponent(k);
                    } else if (k > i) {
                        subMatrix[j - 1][k - 1] = vectors[j].getComponent(k);
                    }
                }
            }
            result += Math.pow(-1, i) * vectors[0].getComponent(i) * new Matrix(subMatrix).getDeterminant();
        }

        return result;
    }

    public Vector multiplyMatrixByVector(Vector vector) {
        Vector result = new Vector(size);

        for (int i = 0; i < size; i++) {
            double sum = 0;

            for (int j = 0; j < size; j++) {
                sum += vectors[i].getComponent(j) * vector.getComponent(j);
            }

            result.setComponent(i, sum);
        }

        return result;
    }

    public void addMatrix(Matrix matrix) {
        if (size != matrix.getSize()) {
            throw new IllegalArgumentException("Матрицы должны быть одинакового размера");
        }

        for (int i = 0; i < size; i++) {
            setVectorRow(i, Vector.sumVectorsStatic(vectors[i], matrix.getVectorRow(i)));
        }

    }

    public void subtractMatrix(Matrix matrix) {
        if (size != matrix.getSize()) {
            throw new IllegalArgumentException("Матрицы должны быть одинакового размера");
        }

        for (int i = 0; i < size; i++) {
            setVectorRow(i, Vector.subtractVectorsStatic(vectors[i], matrix.getVectorRow(i)));
        }
    }

    public static Matrix sumMatricesStatic(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getSize() != matrix2.getSize()) {
            throw new IllegalArgumentException("Матрицы должны быть одинакового размера");
        }

        int size = matrix1.getSize();
        Matrix result = new Matrix(size, size);

        for (int i = 0; i < size; i++) {
            result.setVectorRow(i, Vector.sumVectorsStatic(matrix1.getVectorRow(i), matrix2.getVectorRow(i)));
        }

        return result;
    }

    public static Matrix subtractMatricesStatic(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getSize() != matrix2.getSize()) {
            throw new IllegalArgumentException("Матрицы должны быть одинакового размера");
        }

        int size = matrix1.getSize();
        Matrix result = new Matrix(size, size);

        for (int i = 0; i < size; i++) {
            result.setVectorRow(i, Vector.subtractVectorsStatic(matrix1.getVectorRow(i), matrix2.getVectorRow(i)));
        }

        return result;
    }

    public static Matrix multiplyMatricesStatic(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getSize() != matrix2.getSize()) {
            throw new IllegalArgumentException("Матрицы должны быть одинакового размера");
        }

        int size = matrix1.getSize();
        Matrix result = new Matrix(size, size);


        for (int i = 0; i < size; i++) {
            result.setVectorRow(i, Vector.multiplyVectorsStatic(matrix1.getVectorRow(i), matrix2.getVectorRow(i)));
        }

        return result;
    }
}
