package com.algolab.algorithms;

public class Factorial {
    public static long factorialRecursive(int n, int[] recursiveCounter) {
    recursiveCounter[0]++;

    if (n == 0) {
        return 1;
    }

    return n * factorialRecursive(n - 1, recursiveCounter);
}

public static long factorialIterative(int n, int[] iterationCounter) {

    long factorialResult = 1;

    for (int number = 1; number <= n; number++) {

        iterationCounter[0]++;

        factorialResult = factorialResult * number;
    }

    return factorialResult;
}
}
