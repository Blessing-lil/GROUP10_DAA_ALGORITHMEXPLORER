
package com.algolab.sorting.visualizer;

public class SortingStep {

    private final int[] array;
    private final int comparisons;
    private final int movements;
    private final String description;

    public SortingStep(
            int[] array,
            int comparisons,
            int movements,
            String description) {

        this.array = array.clone();
        this.comparisons = comparisons;
        this.movements = movements;
        this.description = description;
    }

    public int[] getArray() {
        return array.clone();
    }

    public int getComparisons() {
        return comparisons;
    }

    public int getMovements() {
        return movements;
    }

    public String getDescription() {
        return description;
    }
}
