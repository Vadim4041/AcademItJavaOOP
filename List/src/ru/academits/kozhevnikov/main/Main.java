package ru.academits.kozhevnikov.main;

import ru.academits.kozhevnikov.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>(5);
        list.insertFirst(1);
        list.insertFirst(2);
        list.insertFirst(3);
        list.insertFirst(4);

        System.out.println("Список:");
        System.out.println(list);

        System.out.println("Длина списка:");
        System.out.println(list.getCount());

        System.out.println("Получение первого элемента списка:");
        System.out.println(list.getFirst());

        System.out.println("Получение элемента списка с заданным индексом:");
        System.out.println(list.getByIndex(3));

        System.out.println();

        System.out.println("Заменяемый элемент по выбранному индексу:");
        System.out.println(list.setByIndex(3, 6));
        System.out.println("Список:");
        System.out.println(list);

        System.out.println();

        list.insertFirst(7);
        System.out.println("Список после вставки элемента в начало:");
        System.out.println(list);

        System.out.println();

        System.out.println("Удаляемый элемент по заданному индексу:");
        System.out.println(list.removeByIndex(1));
        System.out.println("Список после удаления элемента по заданному индексу:");
        System.out.println(list);

        System.out.println();

        list.insertByIndex(4, 8);
        System.out.println("Список после вставки элемента по заданному индексу:");
        System.out.println(list);

        System.out.println();

        System.out.println("Первый элемент списка, который нужно удалить:");
        System.out.println(list.removeFirst());
        System.out.println("Список после удаления первого элемента:");
        System.out.println(list);

        System.out.println();

        System.out.println("Нашелся ли элемент с заданным значением:");
        System.out.println(list.remove(6));
        System.out.println("Список после удаления найденного элемента:");
        System.out.println(list);

        System.out.println();

        list.reverse();
        System.out.println("Список после разворота:");
        System.out.println(list);

        System.out.println();

        SinglyLinkedList<Integer> listCopy = list.copy();
        System.out.println("Копия списка:");
        System.out.println(listCopy);
    }
}
