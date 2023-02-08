package ru.academits.kozhevnikov.shapes;

public class Rectangle extends Shape {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        super(width, height);
        this.width = width;
        this.height = height;
    }

    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return (width + height) * 2;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
