package ru.academits.kozhevnikov.shapes;

public class Rectangle implements Shape {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
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

    @Override
    public String toString() {
        return "Характеристики выбранного прямоугольника:" + System.lineSeparator()
                + "Ширина: " + getWidth() + System.lineSeparator()
                + "Высота: " + getHeight() + System.lineSeparator()
                + "Площадь: " + getArea() + System.lineSeparator()
                + "Периметр: " + getPerimeter() + System.lineSeparator();
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = (int) (prime * hash + width + height);

        return hash;
    }

    @Override
    public boolean equals(Object shape) {
        if (!(shape instanceof Rectangle)) {
            return false;
        }

        return (shape == this) || (width == ((Rectangle) shape).width && height == ((Rectangle) shape).height);
    }
}
