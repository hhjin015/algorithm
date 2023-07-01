package Codility;

import java.util.Arrays;

public class Solution_MaxCounters {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(Solution(5, new int[]{3, 4, 4, 6, 1, 4, 4, 6, 3, 1, 6, 3})));
    }

    private static int[] Solution(int N, int[] A) {
        int[] counters = new int[N];
        int lastMax = 0;
        int currentMax = 0;

        for (int i = 0; i < A.length; i++) {
            int value = A[i];

            if (value == N + 1) lastMax = currentMax;
            else {
                if (counters[value - 1] <= lastMax) {
                    counters[value - 1] = lastMax + 1;
                } else {
                    ++counters[value - 1];
                }
                currentMax = Math.max(currentMax, counters[value - 1]);
            }
        }

        for (int i = 0; i < counters.length; i++) {
            if (counters[i] < lastMax) counters[i] = lastMax;
        }

        return counters;
    }
}
