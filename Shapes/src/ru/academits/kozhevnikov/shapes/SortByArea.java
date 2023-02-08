package ru.academits.kozhevnikov.shapes;

import java.util.Comparator;

public class SortByArea implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return (int) shape2.getArea() - (int) shape1.getArea();
    }
}
