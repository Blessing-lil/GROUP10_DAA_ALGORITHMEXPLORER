
package com.algolab.datastructures;

import java.util.ArrayList;
import java.util.List;

public class SinglyLinkedList {

    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public SinglyLinkedList() {
        head = null;
        size = 0;
    }

    // INSERT AT END
    public void insert(int value) {

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

    // DELETE FIRST OCCURRENCE OF A VALUE
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

        while (current.next != null &&
                current.next.data != value) {

            current = current.next;
        }

        if (current.next == null) {
            return false;
        }

        current.next = current.next.next;
        size--;

        return true;
    }

    // SEARCH
    public int search(int value) {

        Node current = head;
        int position = 0;

        while (current != null) {

            if (current.data == value) {
                return position;
            }

            current = current.next;
            position++;
        }

        return -1;
    }

    // GET ALL ELEMENTS
    public List<Integer> getElements() {

        List<Integer> elements = new ArrayList<>();

        Node current = head;

        while (current != null) {
            elements.add(current.data);
            current = current.next;
        }

        return elements;
    }

    // TRAVERSE
    public String traverse() {

        StringBuilder result = new StringBuilder();

        result.append("HEAD → ");

        Node current = head;

        while (current != null) {

            result.append(current.data);
            result.append(" → ");

            current = current.next;
        }

        result.append("NULL");

        return result.toString();
    }

    // CLEAR
    public void clear() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }
}
