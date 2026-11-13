
package com.algolab.algorithms;

public class FactorialTest {

    public static void main(String[] args) {

        int n = 5;

        // Test recursive factorial
        int[] recursiveCounter = {0};

        long recursiveStart = System.nanoTime();

        long recursiveResult =
                Factorial.factorialRecursive(n, recursiveCounter);

        long recursiveEnd = System.nanoTime();

        // Test iterative factorial
        int[] iterationCounter = {0};

        long iterativeStart = System.nanoTime();

        long iterativeResult =
                Factorial.factorialIterative(n, iterationCounter);

        long iterativeEnd = System.nanoTime();

        // Display recursive results
        System.out.println("RECURSIVE FACTORIAL");
        System.out.println("Result: " + recursiveResult);
        System.out.println("Recursive Calls: " + recursiveCounter[0]);
        System.out.println("Execution Time: "
                + (recursiveEnd - recursiveStart) + " ns");

        // Display iterative results
        System.out.println("\nITERATIVE FACTORIAL");
        System.out.println("Result: " + iterativeResult);
        System.out.println("Loop Iterations: " + iterationCounter[0]);
        System.out.println("Execution Time: "
                + (iterativeEnd - iterativeStart) + " ns");
    }
}
