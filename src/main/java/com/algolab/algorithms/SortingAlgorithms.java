package com.algolab.algorithms;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

import com.algolab.analysis.SortStats;



public class SortingAlgorithms {
    private static void swap(int[] arr, int i, int j, SortStats stats) {
        if (i == j) return;

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        stats.addSwap();
        stats.addMoves(3);
    }

    // 1. Bubble Sort
    public static void bubbleSort(int[] arr, SortStats stats) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean changed = false;

            for (int j = 0; j < arr.length - 1 - i; j++) {
                stats.addComparison();

                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1, stats);
                    changed = true;
                }
            }

            if (!changed) break;
        }
    }

    // 2. Selection Sort
    public static void selectionSort(int[] arr, SortStats stats) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;

            for (int j = i + 1; j < arr.length; j++) {
                stats.addComparison();

                if (arr[j] < arr[min]) {
                    min = j;
                }
            }

            swap(arr, i, min, stats);
        }
    }

 // 3. Insertion Sort
    public static void insertionSort(int[] arr, SortStats stats) {
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0) {
                stats.addComparison();

                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    stats.addMoves(1);
                    j--;
                } else {
                    break;
                }
            }

            arr[j + 1] = key;
            stats.addMoves(1);
        }
    }

    // 4. Merge Sort
    public static void mergeSort(int[] arr, SortStats stats) {
        int[] temp = new int[arr.length];
        mergeSortHelper(arr, temp, 0, arr.length - 1, stats);
    }

    private static void mergeSortHelper(
            int[] arr, int[] temp, int left, int right, SortStats stats) {

        if (left >= right) return;

        int mid = left + (right - left) / 2;

        mergeSortHelper(arr, temp, left, mid, stats);
        mergeSortHelper(arr, temp, mid + 1, right, stats);

        int i = left;
        int j = mid + 1;
        int k = left;

while (i <= mid && j <= right) {
            stats.addComparison();

            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
            stats.addMoves(1);
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
            stats.addMoves(1);
        }

        while (j <= right) {
            temp[k++] = arr[j++];
            stats.addMoves(1);
        }

        for (int x = left; x <= right; x++) {
            arr[x] = temp[x];
            stats.addMoves(1);
        }
    }

    // 5. Quick Sort using the first element as pivot
    public static void quickSortFirstPivot(int[] arr, SortStats stats) {
        quickSort(arr, stats, false);
    }

    // 6. Quick Sort using a random pivot
    public static void quickSortRandomPivot(int[] arr, SortStats stats) {
        quickSort(arr, stats, true);
    }

    private static void quickSort(
            int[] arr, SortStats stats, boolean randomPivot) {

        if (arr.length < 2) return;

        Random random = new Random();
       Deque<int[]> stack = new ArrayDeque<>();

        stack.push(new int[]{0, arr.length - 1});

        while (!stack.isEmpty()) {
            int[] range = stack.pop();

            int low = range[0];
            int high = range[1];

            if (low >= high) continue;

            if (randomPivot) {
                int randomIndex = low +
                        random.nextInt(high - low + 1);

                swap(arr, low, randomIndex, stats);
            }

            int pivotIndex = partition(arr, low, high, stats);

            if (pivotIndex - 1 > low) {
                stack.push(new int[]{low, pivotIndex - 1});
            }

            if (pivotIndex + 1 < high) {
                stack.push(new int[]{pivotIndex + 1, high});
            }
        }
    }

    private static int partition(
            int[] arr, int low, int high, SortStats stats) {

        int pivot = arr[low];
        int i = low + 1;
        int j = high;

        while (true) {

            while (i <= high) {
                stats.addComparison();

                if (arr[i] > pivot) break;
                i++;
    }

            while (j > low) {
                stats.addComparison();

                if (arr[j] <= pivot) break;
                j--;
            }

            if (i >= j) break;

            swap(arr, i, j, stats);
            i++;
            j--;
        }

        swap(arr, low, j, stats);

        return j;
    }
}

