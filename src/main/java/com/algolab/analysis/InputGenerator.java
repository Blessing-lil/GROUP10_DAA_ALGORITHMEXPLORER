package com.algolab.analysis;

import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class InputGenerator {

    public static int[] generate(int size, String type) {

        if (size < 0) {
            throw new IllegalArgumentException("Input size cannot be negative.");
        }

        int[] data = new int[size];
        Random random = new Random();
        String normalizedType = type.trim().toLowerCase(Locale.ROOT);

        for (int i = 0; i < size; i++) {
            data[i] = random.nextInt(100000);
        }

        if ("sorted".equals(normalizedType)) {
            Arrays.sort(data);
        }
        else if ("reverse".equals(normalizedType)
                || "reverse sorted".equals(normalizedType)) {
            Arrays.sort(data);

            for (int i = 0; i < size / 2; i++) {
                int temp = data[i];
                data[i] = data[size - 1 - i];
                data[size - 1 - i] = temp;
            }
        } else if (!"random".equals(normalizedType)) {
            throw new IllegalArgumentException(
                    "Unsupported input type: " + type
            );
        }

        return data;
    }
}