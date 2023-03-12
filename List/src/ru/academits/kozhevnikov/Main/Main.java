package ru.academits.kozhevnikov.Main;

import ru.academits.kozhevnikov.List.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<Integer> SLL = new SinglyLinkedList<>(5);
        SLL.insertFirst(1);
        SLL.insertFirst(2);
        SLL.insertFirst(3);
        SLL.insertFirst(4);

        System.out.println("Список:");
        System.out.println(SLL);

        System.out.println("Длина списка:");
        System.out.println(SLL.getCount());

        System.out.println("Получение первого элемента списка:");
        System.out.println(SLL.getFirstItem());

        System.out.println("Получение элемента списка с заданным индексом:");
        System.out.println(SLL.getDataByIndex(3));

        System.out.println();

        System.out.println("Заменяемый элемент по выбранному индексу:");
        System.out.println(SLL.setByIndex(3, 6));
        System.out.println("Список:");
        System.out.println(SLL);

        System.out.println();

        SLL.insertFirst(7);
        System.out.println("Список после вставки элемента в начало:");
        System.out.println(SLL);

        System.out.println();

        System.out.println("Удаляемый элемент по заданному индексу:");
        System.out.println(SLL.removeByIndex(1));
        System.out.println("Список после удаления элемента по заданному индексу:");
        System.out.println(SLL);

        System.out.println();

        SLL.insertByIndex(4, 8);
        System.out.println("Список после вставки элемента по заданному индексу:");
        System.out.println(SLL);

        System.out.println();

        System.out.println("Первый элемент списка, который нужно удалить:");
        System.out.println(SLL.removeFirst());
        System.out.println("Список после удаления первого элемента:");
        System.out.println(SLL);

        System.out.println();

        System.out.println("Нашелся ли элемент с заданным значением:");
        System.out.println(SLL.remove(6));
        System.out.println("Список после удаления найденного элемента:");
        System.out.println(SLL);

        System.out.println();

        SLL.reverse();
        System.out.println("Список после разворота:");
        System.out.println(SLL);

        System.out.println();

        SinglyLinkedList<Integer> SLLCopy = SLL.copy();
        System.out.println("Копия списка:");
        System.out.println(SLLCopy);
    }
}
