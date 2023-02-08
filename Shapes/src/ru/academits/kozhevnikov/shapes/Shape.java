package ru.academits.kozhevnikov.shapes;

public class Shape {

    protected double x1;
    protected double x2;
    protected double x3;
    protected double y1;
    protected double y2;
    protected double y3;

    protected double squareSide;
    protected double width;
    protected double height;

    public Shape(double squareSide) {
        this.squareSide = squareSide;
    }

    public Shape(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public Shape(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
    }

    public double getArea() {
        return 0;
    }

    public double getPerimeter() {
        return 0;
    }

    public double getWidth() {
        return 0;
    }

    public double getHeight() {
        return 0;
    }
}

