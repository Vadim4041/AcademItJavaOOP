package ru.academits.kozhevnikov.arraylist;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private final static int DEFAULT_CAPACITY = 10;

    private E[] items;
    private int size;
    private int modCount;

    public ArrayList() {
        //noinspection unchecked
        items = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException(String.format("Передана вместимость списка списка %d. " +
                    "Вместимость списка не может быть меньше 0.", capacity));
        }

        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    private void checkIndex(int index, boolean isUpperBoundIncluded) {
        int maxIndex = isUpperBoundIncluded ? size + 1 : size;

        if (index < 0 || index > maxIndex) {
            throw new IndexOutOfBoundsException(String.format("Передан индекс %d. Индекс должен быть не меньше 0 и не больше %d.", index, maxIndex));
        }
    }

    public void ensureCapacity(int capacity) {
        if (capacity > items.length) {
            items = Arrays.copyOf(items, capacity);
        }
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
        sb.append('[');

        for (int i = 0; i < size; i++) {
            sb.append(items[i]).append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());
        sb.append(']');

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

    private class ArrayListIterator implements Iterator<E> {
        private int currentIndex = -1;
        private final int initialModCount = modCount;

        @Override
        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException(String.format("Элемент с индексом %d является последним.", currentIndex));
            }

            if (modCount != initialModCount) {
                throw new ConcurrentModificationException("Список на массиве был изменен.");
            }

            currentIndex++;
            return items[currentIndex];
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayListIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, size, a.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(items, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(E item) {
        add(size, item);

        return true;
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length == 0 ? 1 : items.length * 2);
    }

    @Override
    public void add(int index, E item) {
        checkIndex(index, true);

        if (size == items.length) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = item;

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
    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        checkIndex(index, true);

        if (c.isEmpty()) {
            return false;
        }

        ensureCapacity(size + c.size());

        System.arraycopy(items, index, items, index + c.size(), size - index);
        modCount++;
        size += c.size();

        int i = index;

        for (E item : c) {
            items[i] = item;
            i++;
        }

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.isEmpty()) {
            return false;
        }

        int initialSize = size;

        for (int i = size - 1; i >= 0; i--) {
            if (c.contains(items[i])) {
                remove(i);
            }
        }

        return initialSize != size;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        int initialSize = size;

        for (int i = size - 1; i >= 0; i--) {
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

        Arrays.fill(items, null);

        modCount++;
        size = 0;
    }

    @Override
    public E get(int index) {
        checkIndex(index, false);

        return items[index];
    }

    @Override
    public E set(int index, E item) {
        checkIndex(index, false);

        E oldItem = items[index];
        items[index] = item;

        return oldItem;
    }


    @Override
    public E remove(int index) {
        checkIndex(index, false);

        E removedItem = items[index];

        System.arraycopy(items, index + 1, items, index, size - index - 1);

        items[size - 1] = null;
        modCount++;
        size--;

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
        for (int i = size - 1; i >= 0; i--) {
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
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException("Данный метод не реализован.");
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException("Данный метод не реализован.");
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("Данный метод не реализован.");
    }
}
