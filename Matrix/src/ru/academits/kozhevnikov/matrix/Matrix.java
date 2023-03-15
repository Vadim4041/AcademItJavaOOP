package ru.academits.kozhevnikov.matrix;

import ru.academits.kozhevnikov.vector.Vector;

public class Matrix {
    private Vector[] rows;

    public Matrix(int rowsQuantity, int columnsQuantity) {
        if (columnsQuantity <= 0 || rowsQuantity <= 0) {
            throw new IllegalArgumentException(String.format("Размеры матрицы должны быть больше нуля." +
                            "Передано следующее количество строк матрицы: %d. " +
                            "Передано следующее количество столбцов матрицы: %d.",
                    rowsQuantity, columnsQuantity));
        }

        rows = new Vector[rowsQuantity];

        for (int i = 0; i < rows.length; i++) {
            rows[i] = new Vector(columnsQuantity);
        }
    }

    public Matrix(Matrix matrix) {
        rows = new Vector[matrix.rows.length];

        for (int i = 0; i < matrix.rows.length; i++) {
            rows[i] = new Vector(matrix.rows[i]);
        }
    }

    public Matrix(double[][] array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("Количество строк массива должно быть больше нуля." +
                    "Передан массив со следующим количеством строк: 0.");
        }

        int maxColumnsQuantity = array[0].length;

        for (double[] e : array) {
            if (maxColumnsQuantity < e.length) {
                maxColumnsQuantity = e.length;
            }
        }

        if (maxColumnsQuantity == 0) {
            throw new IllegalArgumentException("Количество столбцов массива должно быть больше нуля." +
                    " Передан массив со следующим количеством столбцов: 0.");
        }

        rows = new Vector[array.length];

        for (int i = 0; i < array.length; i++) {
            rows[i] = new Vector(maxColumnsQuantity, array[i]);
        }
    }

    public Matrix(Vector[] vectors) {
        if (vectors.length == 0) {
            throw new IllegalArgumentException("Количество строк массива векторов должно быть больше нуля." +
                    " Передан массив векторов со следующим количеством строк: 0.");
        }

        int maxColumnsQuantity = vectors[0].getSize();

        for (Vector e : vectors) {
            if (maxColumnsQuantity < e.getSize()) {
                maxColumnsQuantity = e.getSize();
            }
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
            sb.append(vector).append(", ");
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
        if (index < 0 || index >= rows.length) {
            throw new IndexOutOfBoundsException(String.format("Индекс не может быть меньше нуля и больше %d. " +
                    "Передан индекс строки: %d.", rows.length - 1, index));
        }

        return new Vector(rows[index]);
    }

    public void setRow(int index, Vector row) {
        if (index < 0) {
            throw new IndexOutOfBoundsException(String.format("Индекс не может быть меньше нуля. Передан индекс столбца: %d.", index));
        }

        if (index >= rows.length) {
            throw new IndexOutOfBoundsException(String.format("Индекс не может быть больше %d. " +
                    "Передан индекс строки: %d.", rows.length - 1, index));
        }

        if (row.getSize() != getColumnsQuantity()) {
            throw new IllegalArgumentException(String.format("Размеры строк должны быть одинаковыми." +
                    " Размер новой строки равен %d. Размер старой строки равен %d.", row.getSize(), getColumnsQuantity()));
        }

        rows[index] = new Vector(row);
    }

    public Vector getColumn(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException(String.format("Индекс не может быть меньше нуля. " +
                    "Передан индекс столбца: %d.", index));
        }

        if (index >= getColumnsQuantity()) {
            throw new IndexOutOfBoundsException(String.format("Индекс не может быть больше %d. " +
                    "Передан индекс столбца: %d.", getColumnsQuantity() - 1, index));
        }

        Vector vector = new Vector(rows.length);

        for (int i = 0; i < vector.getSize(); i++) {
            vector.setComponent(i, rows[i].getComponent(index));
        }

        return vector;
    }

    public void transpose() {
        int newRowsQuantity = getColumnsQuantity();
        Vector[] newRows = new Vector[newRowsQuantity];

        for (int i = 0; i < newRowsQuantity; i++) {
            newRows[i] = getColumn(i);
        }

        rows = newRows;
    }

    public void multiplyByNumber(double multiplier) {
        for (Vector row : rows) {
            row.multiplyByNumber(multiplier);
        }
    }

    public double getDeterminant() {
        if (rows.length != getColumnsQuantity()) {
            throw new UnsupportedOperationException(String.format("Матрица должна быть квадратной." +
                    " Количество строк матрицы: %d. Количество столбцов матрицы: %d.", rows.length, getColumnsQuantity()));
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
            throw new IllegalArgumentException(String.format("Размер переданного вектора и количество столбцов матрицы должны быть одинаковыми." +
                    " Размер переданного вектора: %d. Количество столбцов матрицы: %d.", vector.getSize(), getColumnsQuantity()));
        }

        Vector result = new Vector(getRowsQuantity());

        for (int i = 0; i < getRowsQuantity(); i++) {
            result.setComponent(i, Vector.getDotProduct(rows[i], vector));
        }

        return result;
    }

    public void add(Matrix matrix) {
        checkDimensionsEquality(this, matrix);

        for (int i = 0; i < rows.length; i++) {
            rows[i].add(matrix.rows[i]);
        }
    }

    public void subtract(Matrix matrix) {
        checkDimensionsEquality(this, matrix);

        for (int i = 0; i < rows.length; i++) {
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
            throw new IllegalArgumentException(String.format("Количество строк первой матрицы должно быть равно количеству столбцов второй матрицы." +
                            "Количество строк первой матрицы: %d. " +
                            "Количество строк второй матрицы: %d. " +
                            "Количество столбцов первой матрицы: %d. " +
                            "Количество столбцов второй матрицы: %d. ",
                    matrix1.getRowsQuantity(), matrix2.getRowsQuantity(), matrix1.getColumnsQuantity(), matrix2.getColumnsQuantity()));
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

    private static void checkDimensionsEquality(Matrix matrix1, Matrix matrix2) {
        if (matrix1.getColumnsQuantity() != matrix2.getColumnsQuantity() || matrix1.getRowsQuantity() != matrix2.getRowsQuantity()) {
            throw new IllegalArgumentException(String.format("Количество столбцов и строк матриц должно быть одинаковым. " +
                            "Количество строк первой матрицы: %d. " +
                            "Количество строк второй матрицы: %d. " +
                            "Количество столбцов первой матрицы: %d. " +
                            "Количество столбцов второй матрицы: %d. ",
                    matrix1.getRowsQuantity(), matrix2.getRowsQuantity(), matrix1.getColumnsQuantity(), matrix2.getColumnsQuantity()));
        }
    }
}
