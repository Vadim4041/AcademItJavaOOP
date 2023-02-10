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

    @Override
    public String toString() {
        return "Характеристики выбранного квадрата:" + System.lineSeparator()
                + "Ширина: " + getWidth() + System.lineSeparator()
                + "Высота: " + getHeight() + System.lineSeparator()
                + "Площадь: " + getArea() + System.lineSeparator()
                + "Периметр: " + getPerimeter() + System.lineSeparator();
    }

    @Override
    public int hashCode() {
        final int prime = 37;
        int hash = 1;
        hash = (int) (prime * hash + squareSide);

        return hash;
    }

    @Override
    public boolean equals(Object shape) {
        if (!(shape instanceof Square)) {
            return false;
        }

        return (shape == this) || (squareSide == ((Square) shape).squareSide);
    }
}
