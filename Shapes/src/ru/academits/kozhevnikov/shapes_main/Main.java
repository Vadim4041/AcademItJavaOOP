package ru.academits.kozhevnikov.shapes_main;


import ru.academits.kozhevnikov.shapes.*;

public class Main {
    public static void main(String[] args) {
        Shape square = new Square(5);
        Shape rectangle = new Rectangle(5, 2);
        Shape circle = new Circle(5);
        Shape triangle = new Triangle(0,0, 5, 0, 0, 6);

        System.out.println(square.getArea());
        System.out.println(square.getPerimeter());
        System.out.println(square.getWidth());
        System.out.println(square.getHeight());
        System.out.println();
        System.out.println(rectangle.getArea());
        System.out.println(rectangle.getPerimeter());
        System.out.println(rectangle.getWidth());
        System.out.println(rectangle.getHeight());
        System.out.println();
        System.out.println(circle.getArea());
        System.out.println(circle.getPerimeter());
        System.out.println(circle.getWidth());
        System.out.println(circle.getHeight());
        System.out.println();
        System.out.println(triangle.getArea());
        System.out.println(triangle.getPerimeter());
        System.out.println(triangle.getWidth());
        System.out.println(triangle.getHeight());
    }
}
