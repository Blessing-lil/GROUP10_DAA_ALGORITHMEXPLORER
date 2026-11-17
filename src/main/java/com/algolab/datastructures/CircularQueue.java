
package com.algolab.datastructures;

import java.util.ArrayList;
import java.util.List;

public class CircularQueue {

    private final int[] elements;
    private final int capacity;

    private int front;
    private int rear;
    private int size;

    public CircularQueue(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive.");
        }

        this.capacity = capacity;
        this.elements = new int[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public boolean enqueue(int value) {
        if (isFull()) {
            return false;
        }

        rear = (rear + 1) % capacity;

        if (isEmpty()) {
            front = rear;
        }

        elements[rear] = value;
        size++;

        return true;
    }

    public Integer dequeue() {
        if (isEmpty()) {
            return null;
        }

        int removed = elements[front];

        front = (front + 1) % capacity;
        size--;

        if (size == 0) {
            front = 0;
            rear = -1;
        }

        return removed;
    }

    public Integer peek() {
        if (isEmpty()) {
            return null;
        }

        return elements[front];
    }

    public Integer getRearElement() {
        if (isEmpty()) {
            return null;
        }

        return elements[rear];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFrontIndex() {
        return isEmpty() ? -1 : front;
    }

    public int getRearIndex() {
        return isEmpty() ? -1 : rear;
    }

    public boolean isOccupied(int index) {
        checkIndex(index);

        if (isEmpty()) {
            return false;
        }

        return ((index - front + capacity) % capacity) < size;
    }

    public Integer getValueAtIndex(int index) {
        checkIndex(index);

        if (!isOccupied(index)) {
            return null;
        }

        return elements[index];
    }

    public void clear() {
        front = 0;
        rear = -1;
        size = 0;
    }

    public List<Integer> getElements() {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            result.add(elements[(front + i) % capacity]);
        }

        return result;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= capacity) {
            throw new IndexOutOfBoundsException(
                    "Index must be between 0 and " + (capacity - 1)
            );
        }
    }
}
