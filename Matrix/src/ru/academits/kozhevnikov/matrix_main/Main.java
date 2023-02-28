package ru.academits.kozhevnikov.matrix_main;

import ru.academits.kozhevnikov.matrix.Matrix;
import ru.academits.kozhevnikov.vector.Vector;

public class Main {
    public static void main(String[] args) throws Exception {
        Matrix matrix1 = new Matrix(3, 1);
        Matrix matrix2 = new Matrix(matrix1);
        Matrix matrix3 = new Matrix(new double[][]{{1, 2}, {4, 5, 6}, {7, 8}});
        Matrix matrix4 = new Matrix(new Vector[]{new Vector(2), new Vector(new double[]{1, 2, 3}), new Vector(3)}); //new Vector[]{new Vector(2), new Vector(new double[]{1, 2, 3}), new Vector(3)}

        System.out.println("Введены 4 матрицы:");
        System.out.println(matrix1);
        System.out.println(matrix2);
        System.out.println(matrix3);
        System.out.println(matrix4);

        System.out.println();

        System.out.println("Получение количества столбцов матрицы 1:");
        System.out.println(matrix1.getColumnsQuantity());
        System.out.println("Получение строки под индексом 1 матрицы 4:");
        System.out.println(matrix4.getRow(1));

        matrix4.setRow(0, new Vector(new double[]{5, 6, 7}));
        matrix4.setRow(2, new Vector(new double[]{9, 8, 4}));

        System.out.println("Матрица 4:");
        System.out.println(matrix4);

        System.out.println("Второй столбец матрицы 4:");
        System.out.println(matrix4.getColumn(1));

        matrix4.transpose();

        System.out.println("Транспонированная матрица 4:");
        System.out.println(matrix4);

        matrix4.getProduct(2);

        System.out.println("Матрица 4, умноженная на 2:");
        System.out.println(matrix4);

        System.out.println("Матрица 4, умноженная на вектор {1, 2, 3}:");
        System.out.println(matrix4.getProduct(new Vector(new double[]{1, 2, 3})));

        System.out.println();

        System.out.println("Матрица 3:");
        System.out.println(matrix3);
        System.out.println("Матрица 4:");
        System.out.println(matrix4);

        matrix4.add(matrix3);

        System.out.println("Матрица 4, после прибавления к ней матрицы 3:");
        System.out.println(matrix4);

        matrix4.subtract(matrix3);

        System.out.println("Матрица 4, после вычитания из нее матрицы 3:");
        System.out.println(matrix4);

        System.out.println();

        System.out.println("Сумма матриц 4 и 3:");
        System.out.println(Matrix.getSum(matrix4, matrix3));
        System.out.println("Разность матриц 4 и 3:");
        System.out.println(Matrix.getDifference(matrix4, matrix3));
        System.out.println("Произведение матриц 4 и 3:");
        System.out.println(Matrix.getProduct(matrix4, matrix3));

        System.out.println();

        System.out.println("Матрица 4:");
        System.out.println(matrix4);

        System.out.println("Определитель матрицы 4:");
        System.out.println(matrix4.getDeterminant());
    }
}
