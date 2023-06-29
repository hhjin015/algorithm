package Codility;

import java.util.Arrays;

public class Solution_OddOccurrencesInArray {
    public static void main(String[] args) {
        System.out.println(Solution(new int[]{9, 3, 9, 3, 9, 7, 9}));
    }

    private static int Solution(int[] A) {
        if (A.length == 1) return A[0];

        Arrays.sort(A);
        boolean[] hasPair = new boolean[A.length];

        for (int i = 0; i < A.length; i++) {
            if (hasPair[i]) continue;

            for (int j = i + 1; j < A.length; j++) {
                if (A[i] == A[j]) {
                    hasPair[i] = true;
                    hasPair[j] = true;
                    break;
                }
            }
            if (!hasPair[i]) return A[i];
        }

        return 0;
    }
}
