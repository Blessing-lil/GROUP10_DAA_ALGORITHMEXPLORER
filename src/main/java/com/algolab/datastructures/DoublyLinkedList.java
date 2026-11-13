package com.algolab.datastructures;

import java.util.ArrayList;
import java.util.List;

public class DoublyLinkedList {

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    private Node head;
    private int size;

    public void insertAtBeginning(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void insertAtEnd(int value) {
        Node newNode = new Node(value);

        if (head == null) {
            head = newNode;
        } else {
            Node current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = newNode;
        }

        size++;
    }

    public boolean delete(int value) {
        if (head == null) {
            return false;
        }

        if (head.data == value) {
            head = head.next;
            size--;
            return true;
        }

        Node current = head;

        while (current.next != null) {
            if (current.next.data == value) {
                current.next = current.next.next;
                size--;
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public boolean search(int value) {
        Node current = head;

        while (current != null) {
            if (current.data == value) {
                return true;
            }

            current = current.next;
        }

        return false;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    public void clear() {
        head = null;
        size = 0;
    }

    public List<Integer> getElements() {
        List<Integer> result = new ArrayList<>();
        Node current = head;

        while (current != null) {
            result.add(current.data);
            current = current.next;
        }

        return result;
    }

    public String display() {
        StringBuilder result = new StringBuilder();
        Node current = head;

        while (current != null) {
            result.append(current.data);

            if (current.next != null) {
                result.append(" -> ");
            }

            current = current.next;
        }

        result.append(" -> NULL");
        return result.toString();
    }
}
