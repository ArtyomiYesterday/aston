package org.example;

import java.util.*;


public class MyList<E> {

    private static final int INITIAL_CAPACITY = 10;
    private Object[] elements;
    private int size;

    public MyList() {
        elements = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        ensureCapacity(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    public void addAll(Collection<? extends E> c) {
        ensureCapacity(size + c.size());
        for (E element : c) {
            elements[size++] = element;
        }
    }


    public void add(E element) {
        add(size, element);
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = Math.max((int)(elements.length * 1.5), minCapacity);
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }


    public void clear() {
        Arrays.fill(elements, null);
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        return (E) elements[index];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        E removedElement = get(index);
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements = Arrays.copyOf(elements, elements.length - 1);
        return removedElement;
    }

    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(elements[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    public void sort(Comparator<? super E> c) {
        quickSort(elements, 0, size - 1, c);
    }

    private void quickSort(Object[] arr, int low, int high, Comparator<? super E> c) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high, c);
            quickSort(arr, low, pivotIndex - 1, c);
            quickSort(arr, pivotIndex + 1, high, c);
        }
    }

    private int partition(Object[] arr, int low, int high, Comparator<? super E> c) {
        E pivot = (E) arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (c.compare((E) arr[j], pivot) <= 0) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private void swap(Object[] arr, int i, int j) {
        Object temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public String toString(){
        return Arrays.toString(elements);
    }
}

