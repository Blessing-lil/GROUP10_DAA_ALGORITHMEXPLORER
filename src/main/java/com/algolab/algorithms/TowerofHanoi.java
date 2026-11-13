
package com.algolab.algorithms;

public class TowerofHanoi {

    public static void solve(int n, String source,
                             String helper, String destination,
                             String[] moves, int[] count) {

        // Stop when there are no disks left
        if (n == 0) {
            return;
        }

        // Move smaller disks from source to helper
        solve(n - 1, source, destination, helper, moves, count);

        // Record the movement of the largest disk
        moves[count[0]] = "Move disk " + n + " from "
                + source + " to " + destination;

        count[0]++;

        // Move the smaller disks from helper to destination
        solve(n - 1, helper, source, destination, moves, count);
    }

    public static int calculateMoves(int n) {
    int total = 1;

    for (int i = 1; i <= n; i++) {
        total = total * 2;
    }

    return total - 1;
}
}
