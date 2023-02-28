package ru.academits.kozhevnikov.matrix;

import ru.academits.kozhevnikov.vector.Vector;

import java.util.Arrays;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsQuantity, int columnsQuantity) {
        if (columnsQuantity <= 0 || rowsQuantity <= 0) {
            throw new IllegalArgumentException(String.format("Введено следующее количество столбцов матрицы: %d." +
                    " Введено следующее количество строк матрицы: %d." +
                    " Размеры матрицы должны быть больше нуля.", columnsQuantity, rowsQuantity));
        }

        rows = new Vector[rowsQuantity];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(columnsQuantity);
        }
    }

    public Matrix(Matrix matrix) {
        rows = Arrays.copyOf(matrix.rows, matrix.rows.length);
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException(String.format("Введен массив со следующим количеством строк: %d." +
                    " Количество строк массива должно быть больше нуля.", array.length));
        }

        int minColumnsQuantity = array[0].length;
        int maxColumnsQuantity = minColumnsQuantity;

        for (double[] e : array) {
            if (minColumnsQuantity > e.length) {
                minColumnsQuantity = e.length;
            }

            if (maxColumnsQuantity < e.length) {
                maxColumnsQuantity = e.length;
            }
        }

        if (minColumnsQuantity == 0) {
            throw new IllegalArgumentException(String.format("Введен массив со следующим количеством столбцов: %d." +
                    " Количество столбцов массива должно быть больше нуля.", minColumnsQuantity));
        }

        rows = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(maxColumnsQuantity);

            for (int j = 0; j < array[i].length; j++) {
                rows[i].setComponent(j, array[i][j]);
            }
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException(String.format("Введен массив векторов со следующим количеством строк: %d." +
                    " Количество строк массива векторов должно быть больше нуля.", vectors.length));
        }

        int minColumnsQuantity = vectors[0].getSize();
        int maxColumnsQuantity = minColumnsQuantity;

        for (Vector e : vectors) {
            if (minColumnsQuantity > e.getSize()) {
                minColumnsQuantity = e.getSize();
            }

            if (maxColumnsQuantity < e.getSize()) {
                maxColumnsQuantity = e.getSize();
            }
        }

        if (minColumnsQuantity <= 0) {
            throw new IllegalArgumentException(String.format("Введен массив векторов со следующим количеством столбцов: %d." +
                    " Количество столбцов массива векторов должно быть больше нуля.", minColumnsQuantity));
        }

        rows = new Vector[vectors.length];

        for (int i = 0; i < vectors.length; i++) {
            rows[i] = new Vector(maxColumnsQuantity);
            rows[i].add(vectors[i]);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("{");

        for (Vector vector : rows) {
            sb.append(vector);
            sb.append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());
        return sb.append("}").toString();
    }

    public int getColumnsQuantity() {
        return rows[0].getSize();
    }

    public int getRowsQuantity() {
        return rows.length;
    }

    public Vector getRow(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException(String.format("Введен индекс строки: %d. Индекс не может быть меньше нуля.", index));
        }

        if (index >= rows.length) {
            throw new IndexOutOfBoundsException(String.format("Введен индекс строки: %d. Индекс не может быть больше %d.", index, rows.length - 1));
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector newRow) {
        if (index < 0) {
            throw new IndexOutOfBoundsException(String.format("Введен индекс строки: %d. Индекс не может быть меньше нуля.", index));
        }

        if (index >= rows.length) {
            throw new IndexOutOfBoundsException(String.format("Введен индекс строки: %d. Индекс не может быть больше %d.", index, rows.length - 1));
        }

        if (newRow.getSize() != rows[index].getSize()) {
            throw new IllegalArgumentException(String.format("Размер новой строки равен %d. Размер старой строки равен %d." +
                    " Размеры строк должны быть одинаковыми", newRow.getSize(), rows[index].getSize()));
        }

        rows[index] = new Vector(newRow);
    }

    public Vector getColumn(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException(String.format("Введен индекс столбца: %d. Индекс не может быть меньше нуля.", index));
        }

        if (index >= rows[0].getSize()) {
            throw new IndexOutOfBoundsException(String.format("Введен индекс столбца: %d. Индекс не может быть больше %d.", index, rows[0].getSize() - 1));
        }

        Vector vector = new Vector(rows.length);

        for (int i = 0; i < vector.getSize(); i++) {
            vector.setComponent(i, rows[i].getComponent(index));
        }

        return vector;
    }

    public void transpose() {
        int newRowsQuantity = getColumnsQuantity();
        Vector[] vectorsCopy = new Vector[newRowsQuantity];

        for (int i = 0; i < newRowsQuantity; i++) {
            vectorsCopy[i] = getColumn(i);
        }

        rows = vectorsCopy;
    }

    public void getProduct(double multiplier) {
        for (Vector e : rows) {
            e.multiplyByNumber(multiplier);
        }
    }

    public double getDeterminant() throws Exception {
        if (rows.length != getColumnsQuantity()) {
            throw new Exception(String.format("Количество строк матрицы: %d. Количество столбцов матрицы: %d." +
                    " Матрица должна быть квадратной.", rows.length, getColumnsQuantity()));
        }

        int size = getColumnsQuantity();

        if (size == 1) {
            return rows[0].getComponent(0);
        }

        if (size == 2) {
            return rows[0].getComponent(0) * rows[1].getComponent(1) - rows[0].getComponent(1) * rows[1].getComponent(0);
        }

        double result = 0;

        for (int i = 0; i < size; i++) {
            double[][] subMatrix = new double[size - 1][size - 1];

            for (int j = 1; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    if (k < i) {
                        subMatrix[j - 1][k] = rows[j].getComponent(k);
                    } else if (k > i) {
                        subMatrix[j - 1][k - 1] = rows[j].getComponent(k);
                    }
                }
            }

            result += Math.pow(-1, i) * rows[0].getComponent(i) * new Matrix(subMatrix).getDeterminant();
        }

        return result;
    }

    public Vector getProduct(Vector vector) {
        if (vector.getSize() != getColumnsQuantity()) {
            throw new IllegalArgumentException(String.format("Размер введенного вектора: %d. Количество столбцов матрицы: %d." +
                    " Эти значеня должны быть одинаковыми", vector.getSize(), getColumnsQuantity()));
        }

        Vector result = new Vector(getColumnsQuantity());

        for (int i = 0; i < getColumnsQuantity(); i++) {
            result.setComponent(i, Vector.getDotProduct(rows[i], vector));
        }

        return result;
    }

    public void add(Matrix matrix) {
        checkDimensionsEquality(this, matrix);

        for (int i = 0; i < getRowsQuantity(); i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkDimensionsEquality(this, matrix);

        for (int i = 0; i < getRowsQuantity(); i++) {
            rows[i].subtract(matrix.rows[i]);
        }
    }

    public static Matrix getSum(Matrix matrix1, Matrix matrix2) {
        checkDimensionsEquality(matrix1, matrix2);

        Matrix result = new Matrix(matrix1);
        result.add(matrix2);

        return result;
    }

    public static Matrix getDifference(Matrix matrix1, Matrix matrix2) {
        checkDimensionsEquality(matrix1, matrix2);

        Matrix result = new Matrix(matrix1);
        result.subtract(matrix2);

        return result;
    }

    public static Matrix getProduct(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsQuantity() != matrix2.getRowsQuantity()) {
            throw new IllegalArgumentException(String.format("Количество столбцов умножаемой матрицы: %d. " +
                            "Количество строк умножаемой матрицы: %d. " +
                            "Количество столбцов матрицы-множителя: %d. " +
                            "Количество строк матрицы-множителя: %d. " +
                            "Количество строк умножаемой матрицы должно быть равно количеству столбцов матрицы-множителя.",
                    matrix1.getColumnsQuantity(), matrix1.getRowsQuantity(), matrix2.getColumnsQuantity(), matrix2.getRowsQuantity()));
        }

        int newRowsQuantity = matrix1.getRowsQuantity();
        int newColumnsQuantity = matrix2.getColumnsQuantity();
        Matrix result = new Matrix(newRowsQuantity, newColumnsQuantity);

        for (int i = 0; i < newRowsQuantity; i++) {
            for (int j = 0; j < newColumnsQuantity; j++) {
                result.rows[i].setComponent(j, Vector.getDotProduct(matrix1.rows[i], matrix2.getColumn(j)));
            }
        }

        return result;
    }

    public static void checkDimensionsEquality(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsQuantity() != matrix2.getColumnsQuantity() || matrix1.getRowsQuantity() != matrix2.getRowsQuantity()) {
            throw new IllegalArgumentException(String.format("Количество столбцов первой матрицы: %d. " +
                            "Количество строк первой матрицы: %d. " +
                            "Количество столбцов второй матрицы: %d. " +
                            "Количество строк второй матрицы: %d. " +
                            "Количество столбцов и строк матриц должно быть одинаковым.",
                    matrix1.getColumnsQuantity(), matrix1.getRowsQuantity(), matrix2.getColumnsQuantity(), matrix2.getRowsQuantity()));
        }
    }
}
