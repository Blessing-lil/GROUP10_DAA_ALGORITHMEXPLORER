
package com.algolab.analysis;

import java.util.List;

public class AnalysisTest {

    public static void main(String[] args) {

        System.out.println("Starting Time Complexity Analysis...");

        List<AnalysisResult> results =
                ComplexityAnalyzer.analyzeAll(1000, "random");

        System.out.println("\nBENCHMARK RESULTS");
        System.out.println("--------------------------------");

        for (AnalysisResult result : results) {

            System.out.println("Algorithm: " + result.getAlgorithm());
            System.out.println("Execution Time: "
                    + result.getExecutionTimeMs() + " ms");
            System.out.println("Comparisons: "
                    + result.getComparisons());
            System.out.println("Swaps: "
                    + result.getSwaps());
            System.out.println("Moves: "
                    + result.getMoves());

            System.out.println("--------------------------------");
        }

        System.out.println("Analysis completed!");
    }
}
