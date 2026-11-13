package com.algolab.analysis;

import java.util.ArrayList;
import java.util.List;

import com.algolab.algorithms.SortingAlgorithms;


public class ComplexityAnalyzer {

    private static final int REPEATS = 3;
    private static final List<String> ALL_ALGORITHMS = List.of(
            "Bubble Sort",
            "Selection Sort",
            "Insertion Sort",
            "Merge Sort",
            "Quick Sort (First Pivot)",
            "Quick Sort (Random Pivot)"
    );
    private static final List<String> EFFICIENT_ALGORITHMS = List.of(
            "Merge Sort",
            "Quick Sort (First Pivot)",
            "Quick Sort (Random Pivot)"
    );

    public static List<AnalysisResult> analyzeAll(
            int size, String inputType) {

        return analyze(size, inputType, ALL_ALGORITHMS);
    }

    public static List<AnalysisResult> analyzeEfficient(
            int size, String inputType) {

        List<String> algorithms = new ArrayList<>(EFFICIENT_ALGORITHMS);
        if (size > 10_000 && !"random".equalsIgnoreCase(inputType)) {
            algorithms.remove("Quick Sort (First Pivot)");
        }
        return analyze(size, inputType, algorithms);
    }

    private static List<AnalysisResult> analyze(
            int size, String inputType, List<String> algorithms) {

        if (size <= 0) {
            throw new IllegalArgumentException("Input size must be greater than zero.");
        }

        int[] original = InputGenerator.generate(size, inputType);

        List<AnalysisResult> results = new ArrayList<>();

        for (String algorithm : algorithms) {

            long totalTime = 0;
            long totalComparisons = 0;
            long totalSwaps = 0;
            long totalMoves = 0;

            for (int run = 0; run < REPEATS; run++) {

                int[] data = original.clone();
                SortStats stats = new SortStats();

                long start = System.nanoTime();

                switch (algorithm) {

                    case "Bubble Sort":
                        SortingAlgorithms.bubbleSort(data, stats);
                        break;

                    case "Selection Sort":
                        SortingAlgorithms.selectionSort(data, stats);
                        break;

                    case "Insertion Sort":
                        SortingAlgorithms.insertionSort(data, stats);
                        break;

                    case "Merge Sort":
                        SortingAlgorithms.mergeSort(data, stats);
                        break;

                    case "Quick Sort (First Pivot)":
                        SortingAlgorithms.quickSortFirstPivot(data, stats);
                        break;

                    case "Quick Sort (Random Pivot)":
                        SortingAlgorithms.quickSortRandomPivot(data, stats);
                        break;
                }

                long end = System.nanoTime();

                if (!isSorted(data)) {
                    throw new IllegalStateException(
                            algorithm + " did not sort correctly."
                    );
                }

                totalTime += end - start;
                totalComparisons += stats.getComparisons();
                totalSwaps += stats.getSwaps();
                totalMoves += stats.getMoves();
            }

            double averageTime =
                    totalTime / (double) REPEATS / 1_000_000.0;

            results.add(new AnalysisResult(
                    algorithm,
                    averageTime,
                    totalComparisons / (double) REPEATS,
                    totalSwaps / (double) REPEATS,
                    totalMoves / (double) REPEATS
            ));
        }

        return results;
    }

    private static boolean isSorted(int[] data) {

        for (int i = 1; i < data.length; i++) {

            if (data[i - 1] > data[i]) {
                return false;
            }
        }

        return true;
    }
}