
package com.algolab.sorting.visualizer;

import java.util.ArrayList;
import java.util.List;

public class SortingEngine {

    private List<SortingStep> steps;
    private int comparisons;
    private int movements;
    private int[] array;

    public List<SortingStep> generateSteps(int[] input, String algorithm) {

        steps = new ArrayList<>();
        comparisons = 0;
        movements = 0;
        array = input.clone();

        steps.add(new SortingStep(
                array, comparisons, movements, "Initial array"
        ));

        switch (algorithm) {

            case "Bubble Sort":
                bubbleSort();
                break;

            case "Selection Sort":
                selectionSort();
                break;

            case "Insertion Sort":
                insertionSort();
                break;

            case "Merge Sort":
                mergeSort(0, array.length - 1);
                break;

            case "Quick Sort":
                quickSort(0, array.length - 1);
                break;

            default:
                throw new IllegalArgumentException(
                        "Unknown sorting algorithm"
                );
        }

        steps.add(new SortingStep(
                array, comparisons, movements, "Sorting completed"
        ));

        return steps;
    }

    private void record(String description) {
        steps.add(new SortingStep(
                array, comparisons, movements, description
        ));
    }

    // ---------------- BUBBLE SORT ----------------

    private void bubbleSort() {

        for (int i = 0; i < array.length - 1; i++) {

            boolean swapped = false;

            for (int j = 0; j < array.length - i - 1; j++) {

                comparisons++;

                record("Comparing " + array[j] + " and " + array[j + 1]);

                if (array[j] > array[j + 1]) {

                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    movements += 3;
                    swapped = true;

                    record("Swapped " + array[j] + " and " + array[j + 1]);
                }
            }

            if (!swapped) {
                break;
            }
        }
    }

    // ---------------- SELECTION SORT ----------------

    private void selectionSort() {

        for (int i = 0; i < array.length - 1; i++) {

            int minIndex = i;

            for (int j = i + 1; j < array.length; j++) {

                comparisons++;

                record("Comparing " + array[j] + " with " + array[minIndex]);

                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {

                int temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;

                movements += 3;

                record("Swapped positions " + i + " and " + minIndex);
            }
        }
    }

    // ---------------- INSERTION SORT ----------------

    private void insertionSort() {

        for (int i = 1; i < array.length; i++) {

            int key = array[i];
            int j = i - 1;

            while (j >= 0) {

                comparisons++;

                record("Comparing " + array[j] + " with " + key);

                if (array[j] > key) {

                    array[j + 1] = array[j];
                    movements++;

                    record("Moved " + array[j] + " to position " + (j + 1));

                    j--;

                } else {
                    break;
                }
            }

            array[j + 1] = key;
            movements++;

            record("Inserted " + key + " at position " + (j + 1));
        }
    }

    // ---------------- MERGE SORT ----------------

    private void mergeSort(int left, int right) {

        if (left >= right) {
            return;
        }

        int middle = left + (right - left) / 2;

        mergeSort(left, middle);
        mergeSort(middle + 1, right);

        merge(left, middle, right);
    }

    private void merge(int left, int middle, int right) {

        int[] temp = new int[right - left + 1];

        int i = left;
        int j = middle + 1;
        int k = 0;

        while (i <= middle && j <= right) {

            comparisons++;

            record("Comparing " + array[i] + " and " + array[j]);

            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }

        while (i <= middle) {
            temp[k++] = array[i++];
        }

        while (j <= right) {
            temp[k++] = array[j++];
        }

        for (int x = 0; x < temp.length; x++) {

            array[left + x] = temp[x];
            movements++;

            record("Moved " + temp[x] + " to position " + (left + x));
        }
    }

    // ---------------- QUICK SORT ----------------

    private void quickSort(int low, int high) {

        if (low < high) {

            int pivotIndex = partition(low, high);

            quickSort(low, pivotIndex - 1);
            quickSort(pivotIndex + 1, high);
        }
    }

    private int partition(int low, int high) {

        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {

            comparisons++;

            record("Comparing " + array[j] + " with pivot " + pivot);

            if (array[j] <= pivot) {

                i++;

                if (i != j) {

                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;

                    movements += 3;

                    record("Swapped positions " + i + " and " + j);
                }
            }
        }

        if (i + 1 != high) {

            int temp = array[i + 1];
            array[i + 1] = array[high];
            array[high] = temp;

            movements += 3;

            record("Placed pivot " + pivot + " in position " + (i + 1));
        }

        return i + 1;
    }
}
