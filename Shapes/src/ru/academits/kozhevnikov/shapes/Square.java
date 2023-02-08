package ru.academits.kozhevnikov.shapes;

public class Square implements Shape {
    private final double squareSide;

    public Square(double squareSide) {
        this.squareSide = squareSide;
    }

    public double getArea() {
        return squareSide * squareSide;
    }

    public double getPerimeter() {
        return squareSide * 4;
    }

    public double getWidth() {
        return squareSide;
    }

    public double getHeight() {
        return squareSide;
    }
}
