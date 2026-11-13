package com.algolab.algorithms;

public class Fibonacci {
    public static long fibonacci(int n, int counter[]) {
        counter[0]++;
        if (n == 0 || n == 1) {
            return n;
        }
        return fibonacci(n - 1, counter) + fibonacci(n - 2, counter);
    }



    
    public static long fibonacciMemoized(int n, int counter[], long[] memo) {
        counter[0]++;
        if (n == 0 || n == 1) {
            return n;
        }
        if (memo[n] >= 0) {
            return memo[n];
        }
        memo[n] = fibonacciMemoized(n - 1, counter, memo) + fibonacciMemoized(n - 2, counter, memo);
        return memo[n];
    }



 public static long fibonacciBottomUp(int n,int counter[]) {
    //Handle base cases
    if (n == 0 || n == 1) {
        return n;
    }
    //Create an array to store Fibonnaci values
    long [] dyanmicprogramming = new long[n + 1];
    //Store the first two fibonacci numbers
    dyanmicprogramming[0] = 0;
    dyanmicprogramming[1] = 1;

    //Calculate the remaining values in a bottom-up manner
    for (int i = 2; i <= n; i++) {
        counter[0]++;
        dyanmicprogramming[i] = dyanmicprogramming[i - 1] + dyanmicprogramming[i - 2];
    }


    return dyanmicprogramming[n];
  }

}

