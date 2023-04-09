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
        List<Person> peopleList = Arrays.asList(
                new Person("Oleg", 17),
                new Person("Irina", 25),
                new Person("Sergey", 58),
                new Person("Alexandr", 15),
                new Person("Artem", 10),
                new Person("Vasiliy", 45),
                new Person("Vasiliy", 28)
        );

        System.out.println("People list:");
        peopleList.forEach(System.out::println);
        System.out.println();

        // Задача А
        List<String> uniqueNamesList = peopleList.stream()
                .map(Person::name)
                .distinct()
                .toList();

        // Задача Б
        System.out.println(uniqueNamesList.stream().collect(Collectors.joining(", ", "Names: ", ".")));

        System.out.println();

        // Задача В
        final int ADULT_AGE = 18;

        List<Person> underagePeople = peopleList.stream()
                .filter(p -> p.age() < ADULT_AGE)
                .toList();

        System.out.println("Underage people:");
        underagePeople.forEach(System.out::println);

        underagePeople.stream()
                .mapToInt(Person::age)
                .average()
                .ifPresentOrElse(
                        age -> System.out.println("Average age of underage people: " + age),
                        () -> System.out.println("There are no underage people")
                );

        System.out.println();

        // Задача Г
        Map<String, Double> averageAgesByNames = peopleList.stream()
                .collect(Collectors.groupingBy(
                        Person::name,
                        Collectors.averagingInt(Person::age))
                );

        System.out.println("Average age of people with following names:");
        averageAgesByNames.forEach((name, age) -> System.out.printf("%s: %.2f%n", name, age));

        System.out.println();

        // Задача Д
        final int UPPER_LIMIT = 45;
        final int LOWER_LIMIT = 20;

        List<String> peopleNamesList = peopleList.stream()
                .filter(p -> p.age() >= LOWER_LIMIT && p.age() <= UPPER_LIMIT)
                .sorted((p1, p2) -> p2.age() - p1.age())
                .map(Person::name)
                .toList();

        System.out.println("People between the age of 20 and 45 in descending order according to their age:");
        System.out.println(peopleNamesList);
    }
}
