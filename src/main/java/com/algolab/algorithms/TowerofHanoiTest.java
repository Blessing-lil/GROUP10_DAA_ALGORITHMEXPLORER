
package com.algolab.algorithms;

public class TowerofHanoiTest {

    public static void main(String[] args) {

        int numberOfDisks = 3;

        int totalMoves = TowerofHanoi.calculateMoves(numberOfDisks);

        String[] moves = new String[totalMoves];

        int[] count = {0};

        TowerofHanoi.solve(
                numberOfDisks,
                "A",
                "B",
               "C",
                moves,
                count
        );

        System.out.println("Total moves: " + count[0]);

        for (int i = 0; i < count[0]; i++) {
            System.out.println(moves[i]);
        }
    }
}
