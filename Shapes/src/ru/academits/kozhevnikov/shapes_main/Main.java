package ru.academits.kozhevnikov.shapes_main;

import ru.academits.kozhevnikov.shapes.*;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[]{
                new Square(5),
                new Square(4.99),
                new Rectangle(5, 2),
                new Circle(5),
                new Triangle(0, 0, 5, 0, 0, 6),
                new Square(8),
                new Rectangle(7, 3),
                new Circle(9),
                new Triangle(0, 0, 9, 0, 0, 2),
        };

        Arrays.sort(shapes, new SortByArea());

        System.out.println("Характеристики фигуры с максимальной площадью:");
        System.out.println("Ширина: " + shapes[0].getWidth());
        System.out.println("Высота: " + shapes[0].getHeight());
        System.out.println("Площадь: " + shapes[0].getArea());
        System.out.println("Периметр: " + shapes[0].getPerimeter());

        System.out.println();

        Arrays.sort(shapes, new SortByPerimeter());

        System.out.println("Характеристики фигуры со вторым по величине периметром:");
        System.out.println("Ширина: " + shapes[1].getWidth());
        System.out.println("Высота: " + shapes[1].getHeight());
        System.out.println("Площадь: " + shapes[1].getArea());
        System.out.println("Периметр: " + shapes[1].getPerimeter());
    }
}
