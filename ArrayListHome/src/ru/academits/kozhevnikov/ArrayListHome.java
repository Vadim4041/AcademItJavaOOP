package ru.academits.kozhevnikov;
/*
1. Прочитать в список все строки из файла
2. Есть список из целых чисел. Удалить из него все четные числа. В
этой задаче новый список создавать нельзя
3. Есть список из целых чисел, в нём некоторые числа могут
повторяться. Надо создать новый список, в котором будут
элементы первого списка в таком же порядке, но без
повторений
Например, был список [1, 5, 2, 1, 3, 5], должен получиться новый
список [1, 5, 2, 3]
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListHome {
    public static void main(String[] args) {
        // 1 задача:
        if (args.length != 1) {
            System.out.printf("Передано следующее количество аргументов: %d. Необходимо передать один аргумент - путь ко входному файлу.", args.length);

            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]))) {
            ArrayList<String> strings = new ArrayList<>();

            while (reader.ready()) {
                strings.add(reader.readLine());
            }

            System.out.println("Список строк после прочтения файла: " + strings);
        } catch (FileNotFoundException e) {
            System.out.println("Входной файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода");
        }
        // 2 задача:
        ArrayList<Integer> integers = new ArrayList<>(Arrays.asList(4, 6, 3, 7, 34, 12));

        for (int i = 0; i < integers.size(); i++) {
            if (integers.get(i) % 2 == 0) {
                integers.remove(i);
                i--;
            }
        }

        System.out.println("Список целых чисел после удаления всех четных чисел: " + integers);

        // 3 задача:
        ArrayList<Integer> integers1 = new ArrayList<>(Arrays.asList(1, 5, 2, 1, 3, 5, 6, 6));

        for (int i = 0; i < integers1.size(); i++) {
            int numberToCheck = integers1.get(i);

            for (int j = i + 1; j < integers1.size(); j++) {
                if (integers1.get(j) == numberToCheck) {
                    integers1.remove(j);
                    j--;
                }
            }
        }

        System.out.println("Список целых чисел после удаления всех повторяющихся чисел: " + integers1);
    }
}
