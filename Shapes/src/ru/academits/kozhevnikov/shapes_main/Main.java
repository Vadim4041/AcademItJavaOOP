package ru.academits.kozhevnikov.shapes_main;

import ru.academits.kozhevnikov.shapes.*;
import ru.academits.kozhevnikov.shapes.comparators.AreaComparator;
import ru.academits.kozhevnikov.shapes.comparators.PerimeterComparator;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Square(500),
                new Square(4.99),
                new Square(4.99),
                new Rectangle(5, 2),
                new Rectangle(5, 2),
                new Circle(5),
                new Circle(5),
                new Triangle(0, 0, 5, 0, 0, 6),
                new Triangle(0, 0, 5, 0, 0, 6),
                new Square(8),
                new Rectangle(7, 3),
                new Circle(9),
                new Triangle(0, 0, 9, 0, 0, 2),
        };

        System.out.println(shapes[5]);
        System.out.println(shapes[1].equals(shapes[2]));
        System.out.println(shapes[3].equals(shapes[4]));
        System.out.println(shapes[5].equals(shapes[6]));
        System.out.println(shapes[7].equals(shapes[8]));
        System.out.println(shapes[0].equals(shapes[10]));
        System.out.println(shapes[0].hashCode());
        System.out.println(shapes[1].hashCode());
        System.out.println(shapes[2].hashCode());
        System.out.println(shapes[3].hashCode());
        System.out.println(shapes[8].hashCode());

        Arrays.sort(shapes, new AreaComparator());

        System.out.println("Характеристики фигуры с максимальной площадью:");
        System.out.println("Ширина: " + shapes[shapes.length - 1].getWidth());
        System.out.println("Высота: " + shapes[shapes.length - 1].getHeight());
        System.out.println("Площадь: " + shapes[shapes.length - 1].getArea());
        System.out.println("Периметр: " + shapes[shapes.length - 1].getPerimeter());

        System.out.println();

        Arrays.sort(shapes, new PerimeterComparator());

        System.out.println("Характеристики фигуры со вторым по величине периметром:");
        System.out.println("Ширина: " + shapes[shapes.length - 2].getWidth());
        System.out.println("Высота: " + shapes[shapes.length - 2].getHeight());
        System.out.println("Площадь: " + shapes[shapes.length - 2].getArea());
        System.out.println("Периметр: " + shapes[shapes.length - 2].getPerimeter());
    }
}
