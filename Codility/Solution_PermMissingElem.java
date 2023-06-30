package Codility;

import java.util.Arrays;

public class Solution_PermMissingElem {
    public static void main(String[] args) {
        System.out.println(Solution(new int[]{1}));
    }

    private static int Solution(int[] A) {
        Arrays.sort(A);

        if (A.length == 0) return 1;

        for (int i = 0; i < A.length; i++) {
            if (A[i] != i + 1) {
                return i + 1;
            }
        }

        return A[A.length - 1] + 1;
    }
}
