package ru.academits.kozhevnikov.range_main;

import ru.academits.kozhevnikov.range.Range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Range range1 = new Range(10, 30);
        Range range2 = new Range(31, 45);

//        System.out.println("Введите начальное значение диапазона:");
//        range.setFrom(scanner.nextDouble());
//        System.out.println("Начальное значение диапазона: " + range.getFrom());
//
//        System.out.println("Введите конечное значение диапазона:");
//        range.setTo(scanner.nextDouble());
//        System.out.println("Конечное значение диапазона: " + range.getTo());
//
//        System.out.println("Длина диапазона: " + range.getRange());
//
//        System.out.println("Введите число для проверки вхождения в заданный диапазон:");
//        double numberToCheck = scanner.nextDouble();
//
//        if (range.isInside(numberToCheck)) {
//            System.out.println("Число входит в диапазон");
//        } else {
//            System.out.println("Число не входит в диапазон");
//        }
        System.out.println(range1.getIntersectionWith(range2));
        System.out.println();

        range2.setFrom(0);
        range2.setTo(5);
        System.out.println(range1.getIntersectionWith(range2));
        System.out.println();

        range2.setFrom(30);
        range2.setTo(45);
        System.out.println(range1.getIntersectionWith(range2));
        System.out.println();

        range2.setFrom(29);
        range2.setTo(45);
        System.out.println(range1.getIntersectionWith(range2).getFrom());
        System.out.println(range1.getIntersectionWith(range2).getTo());
        System.out.println();

        range2.setFrom(20);
        range2.setTo(30);
        System.out.println(range1.getIntersectionWith(range2).getFrom());
        System.out.println(range1.getIntersectionWith(range2).getTo());
        System.out.println();

        range2.setFrom(15);
        range2.setTo(25);
        System.out.println(range1.getIntersectionWith(range2).getFrom());
        System.out.println(range1.getIntersectionWith(range2).getTo());
        System.out.println();

        range2.setFrom(10);
        range2.setTo(25);
        System.out.println(range1.getIntersectionWith(range2).getFrom());
        System.out.println(range1.getIntersectionWith(range2).getTo());
        System.out.println();

        range2.setFrom(10);
        range2.setTo(30);
        System.out.println(range1.getIntersectionWith(range2).getFrom());
        System.out.println(range1.getIntersectionWith(range2).getTo());
        System.out.println();

    }
}
