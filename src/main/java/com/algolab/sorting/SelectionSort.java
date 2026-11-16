
package com.algolab.sorting;

public class SelectionSort {

    public void sort(int[] array) {

        if (array == null || array.length <= 1) {
            return;
        }

        int n = array.length;

        for (int i = 0; i < n - 1; i++) {

            int minimumIndex = i;

            for (int j = i + 1; j < n; j++) {

                if (array[j] < array[minimumIndex]) {
                    minimumIndex = j;
                }
            }

            if (minimumIndex != i) {

                int temp = array[i];
                array[i] = array[minimumIndex];
                array[minimumIndex] = temp;
            }
        }
    }
}
