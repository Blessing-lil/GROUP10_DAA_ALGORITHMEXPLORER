package com.algolab.analysis;

public class AnalysisResult {

    private String algorithm;
    private double executionTimeMs;
    private double comparisons;
    private double swaps;
    private double moves;

    public AnalysisResult(String algorithm,
                          double executionTimeMs,
                          double comparisons,
                          double swaps,
                          double moves) {

        this.algorithm = algorithm;
        this.executionTimeMs = executionTimeMs;
        this.comparisons = comparisons;
        this.swaps = swaps;
        this.moves = moves;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public double getExecutionTimeMs() {
        return executionTimeMs;
    }

    public double getComparisons() {
        return comparisons;
    }

    public double getSwaps() {
        return swaps;
    }

    public double getMoves() {
        return moves;
    }
}