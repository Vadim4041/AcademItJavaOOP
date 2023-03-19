package ru.academits.kozhevnikov.main;

import ru.academits.kozhevnikov.arrayList.ArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>(5);

        int j = 0;
        for (Integer ignored : arrayList) {
            arrayList.set(j, j);
            j++;
        }


        System.out.println("Список на массиве после создания:");
        System.out.println(arrayList);

        System.out.println();

        System.out.println("Получилось ли вставить в конец списка новый элемент:");
        System.out.println(arrayList.add(10));
        System.out.println("Список на массиве после вставки элемента в конец списка:");
        System.out.println(arrayList);

        System.out.println();

        arrayList.add(4, 12);
        System.out.println("Список на массиве после вставки элемента в список:");
        System.out.println(arrayList);

        System.out.println();

        System.out.println("Удаляем следующий элемент: ");
        System.out.println(arrayList.remove(4));
        System.out.println("Список на массиве после удаления элемента из списка:");
        System.out.println(arrayList);

        System.out.println();

        Object[] array = arrayList.toArray();
        System.out.println("Приводим список на массиве к массиву:");
        System.out.println(Arrays.toString(array));
        System.out.println("Выводим последний элемент массива:");
        System.out.println(array[array.length - 1]);



    }
}
