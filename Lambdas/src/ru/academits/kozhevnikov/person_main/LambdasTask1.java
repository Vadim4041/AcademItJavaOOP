/*
• Создать класс Person с полями имя и возраст. Сделать конструктор,
который принимает эти параметры. Сделать геттеры для полей
• В main создать список из нескольких людей
• При помощи лямбда-функций:
• А) получить список уникальных имен
• Б) вывести список уникальных имен в формате:
Имена: Иван, Сергей, Петр.
• В) получить список людей младше 18, посчитать для них средний
возраст
• Г) при помощи группировки получить Map, в котором ключи –
имена, а значения – средний возраст
• Д) получить людей, возраст которых от 20 до 45, вывести в консоль
их имена в порядке убывания возраста
 */

package ru.academits.kozhevnikov.person_main;

import ru.academits.kozhevnikov.person.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LambdasTask1 {
    public static void main(String[] args) {
        List<Person> peopleList1 = Arrays.asList(
                new Person(17, "Oleg"),
                new Person(25, "Irina"),
                new Person(58, "Sergey"),
                new Person(15, "Alexandr"),
                new Person(10, "Artem"),
                new Person(45, "Vasiliy"),
                new Person(28, "Vasiliy")
        );

        System.out.println("People list:");
        peopleList1.forEach(System.out::println);
        System.out.println();

        // Задача А
        List<String> peopleList2 = peopleList1.stream()
                .map(Person::name)
                .distinct()
                .toList();

        // Задача Б
        System.out.println(peopleList2.stream()
                .collect(Collectors.joining(", ", "Names: ", ".")));

        System.out.println();

        // Задача В
        final int ADULT_AGE = 18;

        List<Person> underagePeople = peopleList1.stream()
                .filter(g -> g.age() < ADULT_AGE)
                .toList();

        System.out.println("Underage people:");
        underagePeople.forEach(System.out::println);

        double underagePeopleAverageAge = underagePeople.stream()
                .collect(Collectors.averagingDouble(Person::age));

        System.out.println("Average age of underage people: " + underagePeopleAverageAge);

        System.out.println();

        // Задача Г
        Map<String, Double> NamesAndAverageAgeMap = peopleList1.stream()
                .collect(Collectors.groupingBy(
                        Person::name,
                        Collectors.averagingDouble(Person::age))
                );

        System.out.println("Average age of people with following names:");
        NamesAndAverageAgeMap.forEach((name, age) -> System.out.printf("%s: %.2f%n", name, age));

        System.out.println();

        // Задача Г
        final int UPPER_LIMIT = 45;
        final int LOWER_LIMIT = 20;

        String peopleList3 = String.valueOf(peopleList1.stream()
                .filter(Person -> Person.age() >= LOWER_LIMIT)
                .filter(Person -> Person.age() <= UPPER_LIMIT)
                .sorted((p1, p2) -> (p2.age() - p1.age()))
                .map(Person::name)
                .toList());

        System.out.println("People between the age of 20 and 45 in descending order according to their age:");
        System.out.println(peopleList3);
    }
}
