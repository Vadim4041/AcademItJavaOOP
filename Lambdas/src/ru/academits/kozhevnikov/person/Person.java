package ru.academits.kozhevnikov.person;

public record Person(int age, String name) {

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
