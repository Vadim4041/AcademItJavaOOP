package ru.academits.kozhevnikov.vector_main;

import ru.academits.kozhevnikov.vector.Vector;

import static ru.academits.kozhevnikov.vector.Vector.*;

public class Main {

    public static void main(String[] args) {
        Vector vector = new Vector(5, new double[]{1, 2, 3});

        System.out.println(vector.getSize());
        System.out.println(vector);

        Vector vector1 = new Vector(vector);
        Vector vector2 = new Vector(new double[]{1, 2, 3});


        System.out.println(vector.getSize());
        System.out.println(vector1);

        System.out.println();

        vector.addVector(vector1);
        System.out.println(vector);
        System.out.println(vector1);

        System.out.println();

        vector.subtractVector(vector1);
        System.out.println(vector);
        System.out.println(vector1);

        System.out.println();

        vector1.multiplyVector(5);
        System.out.println(vector);
        System.out.println(vector1);

        System.out.println();

        vector1.reverseVector();
        System.out.println(vector);
        System.out.println(vector1);

        System.out.println();

        System.out.println(vector1.getComponent(0));

        System.out.println();

        System.out.println(vector);
        System.out.println(vector1);
        System.out.println(vector2);
        System.out.println();
        vector1.setComponent(3, 5);
        System.out.println(vector);
        System.out.println(vector1);
        System.out.println(vector2);

        System.out.println();

        System.out.println(vector.hashCode());
        System.out.println(vector1.hashCode());
        System.out.println(vector.equals(vector1));
        System.out.println(vector.equals(vector2));

        Vector vector3 = new Vector(vector);
        System.out.println(vector.equals(vector3));

        System.out.println();

        System.out.println(sumVectorsStatic(vector, vector1));
        System.out.println(subtractVectorsStatic(vector2, vector));
        System.out.println(multiplyVectorsStatic(vector, vector1));
    }
}
