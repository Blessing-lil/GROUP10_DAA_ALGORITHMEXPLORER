package com.algolab.datastructures;

import java.util.ArrayList;
import java.util.List;

public class Stack {

    private final ArrayList<Integer> elements;
    private final int capacity;

    public Stack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive.");
        }

        this.capacity = capacity;
        this.elements = new ArrayList<>();
    }

    public boolean push(int value) {
        if (isFull()) {
            return false;
        }

        elements.add(value);
        return true;
    }

    public Integer pop() {
        if (isEmpty()) {
            return null;
        }

        return elements.remove(elements.size() - 1);
    }

    public Integer peek() {
        if (isEmpty()) {
            return null;
        }

        return elements.get(elements.size() - 1);
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public boolean isFull() {
        return elements.size() == capacity;
    }

    public int size() {
        return elements.size();
    }

    public int getCapacity() {
        return capacity;
    }

    public void clear() {
        elements.clear();
    }

    public List<Integer> getElements() {
        return new ArrayList<>(elements);
    }
}
