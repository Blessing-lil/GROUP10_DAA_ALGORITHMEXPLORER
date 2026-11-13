package com.algolab.analysis;

public class SortStats {

    private long comparisons;
    private long swaps;
    private long moves;
    private long executionTime;

    public void addComparison() {
        comparisons++;
    }

    public void addSwap() {
        swaps++;
    }

    public void addMoves(long count) {
        moves += count;
    }

    public long getComparisons() {
        return comparisons;
    }

    public long getSwaps() {
        return swaps;
    }

    public long getMoves() {
        return moves;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }
}