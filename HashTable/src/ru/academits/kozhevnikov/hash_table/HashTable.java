package ru.academits.kozhevnikov.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private final static int DEFAULT_CAPACITY = 10;

    private int size;
    private final ArrayList<E>[] lists;
    private int modCount;

    public HashTable() {
        //noinspection unchecked
        lists = new ArrayList[DEFAULT_CAPACITY];
    }

    public HashTable(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException(String.format("Введен размер %d. Размер хэш-таблицы должен быть не меньше 1.", capacity));
        }

        //noinspection unchecked
        lists = new ArrayList[capacity];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                sb.append(i).append(':').append(lists[i]).append(System.lineSeparator());
            }
        }

        sb.append("Размер хэш-таблицы: ").append(size);

        return sb.toString();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private int getIndex(Object o) {
        return o == null ? 0 : Math.abs(o.hashCode() % lists.length);
    }

    @Override
    public boolean contains(Object o) {
        int index = getIndex(o);

        return lists[index] != null && lists[index].contains(o);
    }

    @Override
    public boolean add(E item) {
        int index = getIndex(item);

        if (lists[index] == null) {
            lists[index] = new ArrayList<>();
        }

        lists[index].add(item);
        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }

        for (E e : c) {
            add(e);
        }

        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (isEmpty()) {
            return false;
        }

        int index = getIndex(o);

        if (lists[index] != null && lists[index].remove(o)) {
            size--;
            modCount++;

            return true;
        }

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.isEmpty()) {
            return false;
        }

        int initialSize = size;

        for (ArrayList<E> list : lists) {
            if (list != null) {
                size -= list.size();
                list.removeAll(c);
                size += list.size();
            }
        }

        if (initialSize != size) {
            modCount++;
        }

        return size != initialSize;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c, "Вместо коллекции передан null.");

        int initialSize = size;

        for (ArrayList<E> list : lists) {
            if (list != null) {
                size -= list.size();
                list.retainAll(c);
                size += list.size();
            }
        }

        if (initialSize != size) {
            modCount++;
        }

        return initialSize != size;
    }

    @Override
    public void clear() {
        if (isEmpty()) {
            return;
        }

        Arrays.fill(lists, null);

        modCount++;
        size = 0;
    }

    private class HashTableIterator implements Iterator<E> {
        private final int initialModCount = modCount;
        private int itemIndex = -1;
        private int listIndex = -1;
        private int arrayIndex;

        @Override
        public boolean hasNext() {
            return itemIndex + 1 < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException(String.format("Элемент с индексом %d в хэш-таблице является последним.", itemIndex));
            }

            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("Хэш-таблица была изменена во время итерации.");
            }

            while (true) {
                if (lists[arrayIndex] == null) {
                    arrayIndex++;
                } else {
                    listIndex++;

                    if (listIndex == lists[arrayIndex].size()) {
                        arrayIndex++;
                        listIndex = -1;
                    } else {
                        itemIndex++;
                        return lists[arrayIndex].get(listIndex);
                    }
                }
            }
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] itemsArray = new Object[size];

        int i = 0;

        for (E item : this) {
            itemsArray[i] = item;
            i++;
        }

        return itemsArray;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(toArray(), size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(toArray(), 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }
}
