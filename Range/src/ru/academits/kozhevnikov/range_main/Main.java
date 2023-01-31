package ru.academits.kozhevnikov.range_main;

import ru.academits.kozhevnikov.range.Range;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Range range = new Range(10, 30);

        System.out.println("Введите начальное значение диапазона:");
        range.setFrom(scanner.nextDouble());
        System.out.println("Начальное значение диапазона: " + range.getFrom());

        System.out.println("Введите конечное значение диапазона:");
        range.setTo(scanner.nextDouble());
        System.out.println("Конечное значение диапазона: " + range.getTo());

        System.out.println("Длина диапазона: " + range.getRange());

        System.out.println("Введите число для проверки вхождения в заданный диапазон:");
        double numberToCheck = scanner.nextDouble();

        if (range.isInside(numberToCheck)) {
            System.out.println("Число входит в диапазон");
        } else {
            System.out.println("Число не входит в диапазон");
        }
    }
}
