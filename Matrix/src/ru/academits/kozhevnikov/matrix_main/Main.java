package ru.academits.kozhevnikov.matrix_main;

import ru.academits.kozhevnikov.matrix.Matrix;
import ru.academits.kozhevnikov.vector.Vector;

import static ru.academits.kozhevnikov.matrix.Matrix.*;

public class Main {
    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(3, 1);
        Matrix matrix2 = new Matrix(matrix1);
        Matrix matrix3 = new Matrix(new double[][]{{1, 2}, {4, 5, 6}, {7, 8}});
        Matrix matrix4 = new Matrix(new Vector[]{new Vector(2), new Vector(new double[]{1, 2, 3}), new Vector(3)}); //new Vector[]{new Vector(2), new Vector(new double[]{1, 2, 3}), new Vector(3)}

        System.out.println(matrix1);
        System.out.println(matrix2);
        System.out.println(matrix3);
        System.out.println(matrix4);

        System.out.println();

        System.out.println(matrix1.getSize());
        System.out.println(matrix4.getVectorRow(1));

        matrix4.setVectorRow(0, new Vector(new double[]{5, 6, 7}));
        matrix4.setVectorRow(2, new Vector(new double[]{9, 8, 4}));

        System.out.println(matrix4);

        System.out.println(matrix4.getVectorColumn(1));

        System.out.println("Транспонирование матрицы:");
        matrix4.transposeMatrix();

        System.out.println(matrix4);

        matrix4.multiplyMatrixByNumber(2);

        System.out.println(matrix4);

        System.out.println(matrix4.multiplyMatrixByVector(new Vector(new double[]{1, 2, 3})));

        System.out.println();

        System.out.println(matrix3);
        System.out.println(matrix4);

        matrix4.addMatrix(matrix3);

        System.out.println(matrix4);

        matrix4.subtractMatrix(matrix3);

        System.out.println(matrix4);

        System.out.println();

        System.out.println(sumMatricesStatic(matrix4, matrix3));
        System.out.println(subtractMatricesStatic(matrix4, matrix3));
        System.out.println(multiplyMatricesStatic(matrix4, matrix3));

        System.out.println();

        System.out.println(matrix4);

        System.out.println(matrix4.getDeterminant());
    }
}
