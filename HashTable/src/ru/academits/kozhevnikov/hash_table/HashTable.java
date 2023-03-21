package ru.academits.kozhevnikov.hash_table;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private final static int DEFAULT_CAPACITY = 10;
    private int size;
    private final ArrayList<T>[] hashTableArray;
    private int modCount;

    public HashTable() {
        hashTableArray = (ArrayList<T>[]) new ArrayList[DEFAULT_CAPACITY];
    }

    public HashTable(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException(String.format("Введен размер %d. Размер хэш-таблицы должен быть не меньше 1.", capacity));
        }

        hashTableArray = (ArrayList<T>[]) new ArrayList[capacity];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < hashTableArray.length; i++) {
            if (hashTableArray[i] != null) {
                sb.append(i).append(":").append(hashTableArray[i]).append(System.lineSeparator());
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

    private int getItemHash(Object o) {
        return o == null ? 0 : Math.abs(o.hashCode() % hashTableArray.length);
    }

    @Override
    public boolean contains(Object o) {
        int itemHash = getItemHash(o);

        if (hashTableArray[itemHash] == null) {
            return false;
        }

        return hashTableArray[itemHash].contains(o);
    }

    @Override
    public boolean add(T t) {
        int itemHash = getItemHash(t);

        if (hashTableArray[itemHash] == null) {
            hashTableArray[itemHash] = new ArrayList<>();
        }

        hashTableArray[itemHash].add(t);
        size++;
        modCount++;

        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c.size() == 0) {
            return false;
        }

        for (T e : c) {
            add(e);
        }

        return false;
    }

    @Override
    public boolean remove(Object o) {
        if (size == 0) {
            return false;
        }

        int objectHash = getItemHash(o);

        if (hashTableArray[objectHash] == null) {
            return false;
        }

        if (hashTableArray[objectHash].remove(o)) {
            size--;
            modCount++;

            return true;
        }

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }

        int initialSize = size;

        for (Object e : c) {
            this.remove(e);
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
        int initialSize = size;

        for (ArrayList<T> list : hashTableArray) {
            if (list == null || list.size() == 0) {
                continue;
            }

            int listInitialSize = list.size();

            if (list.retainAll(c)) {
                size -= listInitialSize - list.size();
                modCount++;
            }
        }

        return initialSize != size;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        Arrays.fill(hashTableArray, null);

        modCount++;
        size = 0;
    }

    private class HashTableIterator implements Iterator<T> {
        private final int initialModCount = modCount;
        private int itemIndex = -1;
        private int listIndex = -1;
        private int arrayIndex = 0;

        @Override
        public boolean hasNext() {
            return itemIndex + 1 < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException(String.format("Элемент с индексом %d в хэш-таблице является последним.", itemIndex));
            }

            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("Хэш-таблица была изменена во время итерации.");
            }

            while (true) {
                if (hashTableArray[arrayIndex] == null) {
                    arrayIndex++;
                } else {
                    listIndex++;

                    if (listIndex == hashTableArray[arrayIndex].size()) {
                        arrayIndex++;
                        listIndex = -1;
                    } else {
                        itemIndex++;
                        return hashTableArray[arrayIndex].get(listIndex);
                    }
                }
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new HashTableIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] itemsArray = new Object[size];

        int i = 0;

        for (T item : this) {
            itemsArray[i] = item;
            i++;
        }

        return itemsArray;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            return (T1[]) Arrays.copyOf(toArray(), size);
        }

        System.arraycopy(toArray(), 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }
}
