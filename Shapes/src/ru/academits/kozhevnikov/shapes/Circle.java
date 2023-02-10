package ru.academits.kozhevnikov.shapes;

public class Circle implements Shape {
    private final double radius;

    public Circle(double radius) {
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

    @Override
    public String toString() {
        return "Характеристики выбранного круга:" + System.lineSeparator()
                + "Ширина: " + getWidth() + System.lineSeparator()
                + "Высота: " + getHeight() + System.lineSeparator()
                + "Площадь: " + getArea() + System.lineSeparator()
                + "Периметр: " + getPerimeter() + System.lineSeparator();
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = (int) (prime * hash + radius);

        return hash;
    }

    @Override
    public boolean equals(Object shape) {
        if (!(shape instanceof Circle)) {
            return false;
        }

        return (shape == this) || (radius == ((Circle) shape).radius);
    }
}
