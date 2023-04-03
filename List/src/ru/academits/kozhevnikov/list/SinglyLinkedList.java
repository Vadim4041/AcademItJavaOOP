package ru.academits.kozhevnikov.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public SinglyLinkedList() {
    }

    public SinglyLinkedList(T data) {
        head = new ListItem<>(data);
        count = 1;
    }

    @Override
    public String toString() {
        if (count == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append('[');

        for (ListItem<T> item = head; item != null; item = item.getNext()) {
            sb.append(item.getData());
            sb.append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());
        sb.append(']');

        return sb.toString();
    }

    public int getCount() {
        return count;
    }

    public T getFirst() {
        if (count == 0) {
            throw new NoSuchElementException("Список пуст.");
        }

        return head.getData();
    }

    private void checkIndex(int index, boolean includeUpperBound) {
        int upperBound = includeUpperBound ? count + 1 : count;

        if (index < 0 || index >= upperBound) {
            throw new IndexOutOfBoundsException(String.format("Передан индекс %d. Индекс должен быть не меньше 0 и не больше %d.", index, upperBound - 1));
        }
    }

    private ListItem<T> getItemByIndex(int index) {
        ListItem<T> listItem = head;

        for (int i = 0; i < index; i++) {
            listItem = listItem.getNext();
        }

        return listItem;
    }

    public T getByIndex(int index) {
        checkIndex(index, false);

        return getItemByIndex(index).getData();
    }

    public T setByIndex(int index, T data) {
        checkIndex(index, false);

        ListItem<T> itemByIndex = getItemByIndex(index);
        T oldData = itemByIndex.getData();
        itemByIndex.setData(data);

        return oldData;
    }

    public T removeFirst() {
        if (count == 0) {
            throw new NoSuchElementException("Список пуст.");
        }

        T removedData = head.getData();
        head = head.getNext();
        count--;

        return removedData;
    }

    public T removeByIndex(int index) {
        checkIndex(index, false);

        if (index == 0) {
            return removeFirst();
        }

        ListItem<T> previousItem = getItemByIndex(index - 1);

        T removedData = previousItem.getNext().getData();
        previousItem.setNext(previousItem.getNext().getNext());
        count--;

        return removedData;
    }

    public void insertFirst(T data) {
        head = new ListItem<>(data, head);

        count++;
    }

    public void insertByIndex(int index, T data) {
        checkIndex(index, true);

        if (index == 0) {
            insertFirst(data);
            return;
        }

        ListItem<T> previousItem = getItemByIndex(index - 1);
        previousItem.setNext(new ListItem<>(data, previousItem.getNext()));

        count++;
    }

    public boolean remove(T data) {
        ListItem<T> currentItem = head;
        ListItem<T> previousItem = null;

        while (currentItem != null) {
            if (Objects.equals(data, currentItem.getData())) {
                if (previousItem != null) {
                    previousItem.setNext(currentItem.getNext());
                    count--;

                    return true;
                }

                removeFirst();
                return true;
            }

            previousItem = currentItem;
            currentItem = currentItem.getNext();
        }

        return false;
    }

    public void reverse() {
        if (count <= 1) {
            return;
        }

        ListItem<T> previousItem = head;
        ListItem<T> currentItem = head.getNext();

        head = currentItem;
        currentItem = currentItem.getNext();
        previousItem.setNext(null);

        while (currentItem != null) {
            head.setNext(previousItem);
            previousItem = head;
            head = currentItem;
            currentItem = currentItem.getNext();
        }

        head.setNext(previousItem);
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> copy = new SinglyLinkedList<>();

        if (count == 0) {
            return copy;
        }

        copy.head = new ListItem<>(head.getData());
        ListItem<T> previousItemCopy = copy.head;

        for (ListItem<T> item = head.getNext(); item != null; item = item.getNext()) {
            ListItem<T> copyItem = new ListItem<>(item.getData());
            previousItemCopy.setNext(copyItem);
            previousItemCopy = copyItem;
        }

        copy.count = count;

        return copy;
    }
}
