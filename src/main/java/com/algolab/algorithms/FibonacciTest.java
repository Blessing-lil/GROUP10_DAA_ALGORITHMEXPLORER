package com.algolab.algorithms;


import java.util.Arrays;
public class FibonacciTest {

    public static void main(String[] args) {

        // 1. Choose the input
        int n = 3;

        // 2. Create the counter
        int[] counter = {0};

        // 3. Record starting time
        long start = System.nanoTime();

        // 4. Call Fibonacci
        long result = Fibonacci.fibonacci(n, counter);

        // 5. Record ending time
        long end = System.nanoTime();

        // 6. Calculate execution time
        long duration = end - start;

        // 7. Display results
        System.out.println("Input: " + n);
        System.out.println("Fibonacci result: " + result);
        System.out.println("Recursive calls: " + counter[0]);
        System.out.println("Execution time: " + duration + " ns");




         

      long[] memo = new long[n + 1];

      // Mark every position as not calculated
       Arrays.fill(memo, -1);

      int[] memoCounter = {0};

     long memoStart = System.nanoTime();

      long memoResult = Fibonacci.fibonacciMemoized(n, memoCounter, memo);

      long memoEnd = System.nanoTime();
 
       System.out.println("\nMemoized Fibonacci:");
       System.out.println("Result: " + memoResult);
        System.out.println("Recursive calls: " + memoCounter[0]);
       System.out.println("Execution time: " + (memoEnd - memoStart) + " ns");




       // Test bottom-up Fibonacci
       int[] bottomCounter = {0};

        long bottomStart = System.nanoTime();

       long bottomResult = Fibonacci.fibonacciBottomUp(n, bottomCounter);

       long bottomEnd = System.nanoTime();

         System.out.println("\nBottom-up Fibonacci:");
          System.out.println("Result: " + bottomResult);
         System.out.println("Loop iterations: " + bottomCounter[0]);
         System.out.println("Execution time: " + (bottomEnd - bottomStart) + " ns");
}
  
}