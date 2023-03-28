package ru.academits.kozhevnikov.hash_table_main;

import ru.academits.kozhevnikov.hash_table.HashTable;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer> hashTable = new HashTable<>();
        hashTable.add(15);
        hashTable.add(2);
        hashTable.add(45);
        hashTable.add(16);
        hashTable.add(10);
        hashTable.add(3);

        System.out.println("Хэш-таблица после создания:");
        System.out.println(hashTable);

        System.out.println();

        System.out.println("Преобразование хэш-таблицы в массив:");
        System.out.println(Arrays.toString(hashTable.toArray()));

        System.out.println();

        System.out.println("Хэш-таблица после удаления элементов, не содержащихся в списке:");
        System.out.println(hashTable.retainAll(List.of(1, 2, 15, 3)));
        System.out.println(hashTable);

        System.out.println();

        System.out.println("Хэш-таблица после удаления всех элементов:");
        hashTable.clear();
        System.out.println(hashTable);
    }
}
