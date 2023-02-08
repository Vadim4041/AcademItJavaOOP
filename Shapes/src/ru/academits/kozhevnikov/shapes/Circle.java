package ru.academits.kozhevnikov.shapes;

public class Circle extends Shape {
    private final double radius;

    public Circle(double radius) {
        super(radius);
        this.radius = radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getPerimeter() {
        return Math.PI * radius * 2;
    }

    public double getWidth() {
        return radius * 2;
    }

    public double getHeight() {
        return radius * 2;
    }
}
