package Codility;

import java.util.Arrays;

public class Solution_PermCheck {
    public static void main(String[] args) {
        System.out.println(Solution(new int[]{4, 1, 3, 2}));
    }

    private static int Solution(int[] A) {
        Arrays.sort(A);

        for (int i = 0; i < A.length; i++) {
            if (i + 1 != A[i]) return 0;
        }
        return 1;
    }
}
