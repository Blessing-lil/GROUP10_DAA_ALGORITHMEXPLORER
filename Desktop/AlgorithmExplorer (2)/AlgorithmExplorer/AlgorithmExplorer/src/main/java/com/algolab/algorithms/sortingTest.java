
package com.algolab.algorithms;

import java.util.Arrays;

public class sortingTest {

    public static void main(String[] args) {

        int[] numbers = {5, 2, 8, 1, 4};

        System.out.println("==========================================");
        System.out.println("           ALGORITHM EXPLORER");
        System.out.println("          SORTING ALGORITHM TEST");
        System.out.println("==========================================");

        System.out.println("\nOriginal Array: "
                + Arrays.toString(numbers));

        // BUBBLE SORT
        int[] bubble = numbers.clone();
        BubbleSort.sort(bubble);
        displayResults("BUBBLE SORT", bubble);

        // SELECTION SORT
        int[] selection = numbers.clone();
        SelectionSort.sort(selection);
        displayResults("SELECTION SORT", selection);

        // INSERTION SORT
        int[] insertion = numbers.clone();
        InsertionSort.sort(insertion);
        displayResults("INSERTION SORT", insertion);

        // MERGE SORT
        int[] merge = numbers.clone();
        MergeSort.sort(merge);
        displayResults("MERGE SORT", merge);

        // FINAL VERIFICATION
        boolean allCorrect =
                isSorted(bubble)
                && isSorted(selection)
                && isSorted(insertion)
                && isSorted(merge);

        System.out.println("\n==========================================");
        System.out.println("             FINAL SUMMARY");
        System.out.println("==========================================");

        System.out.println("Bubble Sort: "
                + Arrays.toString(bubble));

        System.out.println("Selection Sort: "
                + Arrays.toString(selection));

        System.out.println("Insertion Sort: "
                + Arrays.toString(insertion));

        System.out.println("Merge Sort: "
                + Arrays.toString(merge));

        if (allCorrect) {
            System.out.println("\nAll sorting algorithms passed!");
        } else {
            System.out.println("\nSome sorting algorithms failed.");
        }

        System.out.println("==========================================");
    }

    public static void displayResults(
            String algorithmName, int[] result) {

        System.out.println("\n==========================================");
        System.out.println("          " + algorithmName);
        System.out.println("==========================================");

        System.out.println("Sorted Array: "
                + Arrays.toString(result));
    }

    public static boolean isSorted(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {

            if (array[i] > array[i + 1]) {
                return false;
            }
        }

        return true;
    }
}
