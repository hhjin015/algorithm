package Codility;

import java.util.Arrays;

public class Solution_CyclicRotation {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(Solution(new int[]{3, 8, 9, 7, 6}, 3)));
        System.out.println(Arrays.toString(Solution(new int[]{}, 3)));
    }

    private static int[] Solution(int[] A, int K) {
        if (A.length == 0) return new int[]{};

        int lastIdx = A.length - 1;

        for (int t = 0; t < K; t++) {
            int temp = A[lastIdx];

            for (int i = lastIdx; i > 0; i--) {
                A[i] = A[i - 1];
            }
            A[0] = temp;
        }

        return A;
    }
}
