package ru.academits.kozhevnikov.shapes;

import java.util.Comparator;

public class SortByPerimeter implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return (int) shape2.getPerimeter() - (int) shape1.getPerimeter();
    }
}
