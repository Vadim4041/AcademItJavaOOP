package ru.academits.kozhevnikov.arrayList;

import java.util.*;

public class ArrayList<T> implements List<T> {
    private T[] items;
    private int size;
    private final int DEFAULT_CAPACITY = 10;
    private int modCount;

    public ArrayList() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
        size = DEFAULT_CAPACITY;
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException(String.format("Введен размер списка %d. Размер списка не может быть меньше 0.", capacity));
        }

        items = (T[]) new Object[capacity];
        size = items.length;
    }

    private void checkIndex(int index, boolean isUpperBoundIncluded) {
        int maxIndex = isUpperBoundIncluded ? size + 1 : size;

        if (index < 0 || index > maxIndex) {
            throw new IndexOutOfBoundsException(String.format("Передан индекс %d. Индекс должен быть не меньше 0 и не больше %d.", index, maxIndex));
        }
    }

    public void ensureCapacity(int capacity) {
        if (capacity <= items.length) {
            return;
        }

        items = Arrays.copyOf(items, capacity);
    }

    public void trimToSize() {
        if (items.length > size) {
            items = Arrays.copyOf(items, size);
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (int i = 0; i < size; i++) {
            sb.append(items[i]);
            sb.append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());
        sb.append("]");

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

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    private class ArrayListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private final int initialModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException(String.format("Элемент с индексом %d является последним.", currentIndex));
            }

            if (modCount != initialModCount) {
                throw new ConcurrentModificationException("ArrayList has been modified");
            }

            currentIndex++;
            return items[currentIndex];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            return (T1[]) Arrays.copyOf(items, size);
        }

        System.arraycopy(items, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(T t) {
        add(size, t);

        return true;
    }

    @Override
    public void add(int index, T element) {
        checkIndex(index, true);

        if (size == items.length) {
            ensureCapacity(size == 0 ? DEFAULT_CAPACITY : size * 2);
        }

        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = element;

        modCount++;
        size++;
    }


    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object item : c) {
            if (!contains(item)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (c.size() == 0) {
            return false;
        }

        checkIndex(index, true);
        ensureCapacity(size + c.size());

        System.arraycopy(items, index, items, index + c.size(), size - index);
        modCount++;
        size += c.size();

        int currentIndex = index;

        for (T object : c) {
            items[currentIndex] = object;
            currentIndex++;
        }

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.size() == 0) {
            return false;
        }

        int initialSize = size;

        for (int i = size - 1; i != 0; i--) {
            if (c.contains(items[i])) {
                remove(i);
            }
        }

        return initialSize != size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int initialSize = size;

        for (int i = size - 1; i != 0; i--) {
            if (!c.contains(items[i])) {
                remove(i);
            }
        }

        return size != initialSize;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (int i = 0; i < size; i++) {
            items[i] = null;
        }

        modCount++;
        size = 0;
    }

    @Override
    public T get(int index) {
        checkIndex(index, false);

        return items[index];
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index, false);

        T oldItem = items[index];
        items[index] = element;

        return oldItem;
    }


    @Override
    public T remove(int index) {
        checkIndex(index, false);

        T removedItem = items[index];

        System.arraycopy(items, index + 1, items, index, size - index - 1);

        items[size - 1] = null;
        modCount++;
        --size;

        return removedItem;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i != 0; i--) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public boolean remove(Object o) {
        if (size == 0) {
            return false;
        }

        int index = indexOf(o);

        if (index != -1) {
            remove(index);

            return true;
        }

        return false;
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("Данный метод не реализован.");
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("Данный метод не реализован.");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Данный метод не реализован.");
    }
}
