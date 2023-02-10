package ru.academits.kozhevnikov.shapes;

public interface Shape {
    double getArea();

    double getPerimeter();

    double getWidth();

    double getHeight();

    String toString();

    boolean equals(Object shape);

    int hashCode();
}
