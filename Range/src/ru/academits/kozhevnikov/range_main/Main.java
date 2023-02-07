package ru.academits.kozhevnikov.range_main;

import ru.academits.kozhevnikov.range.Range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Range range1 = new Range(10, 30);
        Range range2 = new Range(31, 45);

        System.out.println("Введите начальное значение диапазона:");
        range1.setFrom(scanner.nextDouble());
        System.out.println("Начальное значение диапазона: " + range1.getFrom());

        System.out.println("Введите конечное значение диапазона:");
        range1.setTo(scanner.nextDouble());
        System.out.println("Конечное значение диапазона: " + range1.getTo());

        System.out.println("Длина диапазона: " + range1.getBoundaryDifference());

        System.out.println("Введите число для проверки вхождения в заданный диапазон:");
        double numberToCheck = scanner.nextDouble();

        if (range1.isInside(numberToCheck)) {
            System.out.println("Число входит в диапазон");
        } else {
            System.out.println("Число не входит в диапазон");
        }

        System.out.println("Пересечение:");
        range1.setFrom(10);
        range1.setTo(30);
        System.out.println(range1.getIntersection(range2));
        System.out.println();

        range2.setFrom(0);
        range2.setTo(5);
        System.out.println(range1.getIntersection(range2));
        System.out.println();

        range2.setFrom(30);
        range2.setTo(45);
        System.out.println(range1.getIntersection(range2));
        System.out.println();

        range2.setFrom(29);
        range2.setTo(45);
        System.out.println(range1.getIntersection(range2).getFrom());
        System.out.println(range1.getIntersection(range2).getTo());
        System.out.println();

        range2.setFrom(20);
        range2.setTo(30);
        System.out.println(range1.getIntersection(range2).getFrom());
        System.out.println(range1.getIntersection(range2).getTo());
        System.out.println();

        range2.setFrom(15);
        range2.setTo(25);
        System.out.println(range1.getIntersection(range2).getFrom());
        System.out.println(range1.getIntersection(range2).getTo());
        System.out.println();

        range2.setFrom(10);
        range2.setTo(25);
        System.out.println(range1.getIntersection(range2).getFrom());
        System.out.println(range1.getIntersection(range2).getTo());
        System.out.println();

        range2.setFrom(10);
        range2.setTo(30);
        System.out.println(range1.getIntersection(range2).getFrom());
        System.out.println(range1.getIntersection(range2).getTo());
        System.out.println();
        System.out.println("Cложение:");
        range1.setFrom(10);
        range1.setTo(30);
        System.out.println(Range.toString(range1.getUnion(range2)));
        System.out.println();

        range2.setFrom(30);
        range2.setTo(45);
        System.out.println(Range.toString(range1.getUnion(range2)));
        System.out.println();

        range2.setFrom(29);
        range2.setTo(45);
        System.out.println(Range.toString(range1.getUnion(range2)));
        System.out.println();

        range2.setFrom(10);
        range2.setTo(30);
        System.out.println(Range.toString(range1.getUnion(range2)));
        System.out.println();

        range2.setFrom(0);
        range2.setTo(10);
        System.out.println(Range.toString(range1.getUnion(range2)));
        System.out.println();

        range2.setFrom(0);
        range2.setTo(5);
        System.out.println(Range.toString(range1.getUnion(range2)));
        System.out.println();

        range2.setFrom(0);
        range2.setTo(0);
        range1.setFrom(0);
        range1.setTo(0);
        System.out.println(Range.toString(range1.getUnion(range2)));
        System.out.println();

        System.out.println("Вычитание:");
        range1.setFrom(10);
        range1.setTo(30);
        System.out.println(Range.toString(range1.getSubtraction(range2)));
        System.out.println();

        range2.setFrom(10);
        range2.setTo(30);
        System.out.println(Range.toString(range1.getSubtraction(range2)));
        System.out.println();

        range2.setFrom(20);
        range2.setTo(45);
        System.out.println(Range.toString(range1.getSubtraction(range2)));
        System.out.println();

        range2.setFrom(0);
        range2.setTo(15);
        System.out.println(Range.toString(range1.getSubtraction(range2)));
        System.out.println();

        range2.setFrom(0);
        range2.setTo(5);
        System.out.println(Range.toString(range1.getSubtraction(range2)));
        System.out.println();
    }
}
