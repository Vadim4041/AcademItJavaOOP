package ru.academits.kozhevnikov.List;

import java.util.NoSuchElementException;

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
        sb.append("[");

        for (ListItem<T> item = head; item != null; item = item.getNext()) {
            sb.append(item.getData());
            sb.append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");

        return sb.toString();
    }

    public int getCount() {
        return count;
    }

    public T getFirstItem() {
        if (count == 0) {
            throw new NoSuchElementException("Список пуст.");
        }

        return head.getData();
    }

    private void checkIndex(int index, boolean isUpperBoundIncluded) {
        int upperBound = count - 1;

        if (isUpperBoundIncluded) {
            upperBound++;
        }

        if (index < 0 || index > upperBound) {
            throw new IndexOutOfBoundsException(String.format("Передан индекс %d. Индекс должен быть не меньше 0 и не больше %d.", index, upperBound));
        }
    }

    private ListItem<T> getItemByIndex(int index) {
        checkIndex(index, false);

        ListItem<T> e = head;
        int i = 0;

        while (i != index) {
            e = e.getNext();
            i++;
        }

        return e;
    }

    public T getDataByIndex(int index) {
        return getItemByIndex(index).getData();
    }

    public T setByIndex(int index, T data) {
        checkIndex(index, false);

        T oldData = getDataByIndex(index);
        getItemByIndex(index).setData(data);

        return oldData;
    }

    public T removeFirst() {
        if (count == 0) {
            throw new NoSuchElementException("Список пуст.");
        }

        T oldData = head.getData();
        head = head.getNext();
        count--;

        return oldData;
    }

    public T removeByIndex(int index) {
        checkIndex(index, false);

        if (index == 0) {
            return removeFirst();
        }

        T oldData = getDataByIndex(index);
        getItemByIndex(index - 1).setNext(getItemByIndex(index).getNext());
        count--;

        return oldData;
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

        ListItem<T> newItem = new ListItem<>(data, getItemByIndex(index - 1).getNext());
        getItemByIndex(index - 1).setNext(newItem);

        count++;
    }

    public boolean remove(T data) {
        ListItem<T> currentItem = head;
        ListItem<T> previousItem = null;

        while (currentItem != null) {
            if (currentItem.getData().equals(data)) {
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

        ListItem<T> previous = head;
        ListItem<T> current = head.getNext();

        head = current;
        current = current.getNext();
        previous.setNext(null);

        while (current != null) {
            head.setNext(previous);
            previous = head;
            head = current;
            current = current.getNext();
        }

        head.setNext(previous);
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> copy = new SinglyLinkedList<>();

        if (count == 0) {
            return copy;
        }

        copy.head = new ListItem<>(head.getData(), head.getNext());
        ListItem<T> previous = copy.head;

        for (ListItem<T> item = head.getNext(); item != null; item = item.getNext()) {
            ListItem<T> itemCopy = new ListItem<>(item.getData());
            previous.setNext(itemCopy);
            previous = itemCopy;
        }

        copy.count = count;

        return copy;
    }
}
