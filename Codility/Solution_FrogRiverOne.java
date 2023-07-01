package Codility;

public class Solution_FrogRiverOne {
    public static void main(String[] args) {
        System.out.println(Solution(3, new int[]{1, 3, 1, 3, 2, 1, 3}));
    }

    private static int Solution(int X, int[] A) {
        boolean[] B = new boolean[X + 1];
        int cnt = 0;

        for (int i = 0; i < A.length; i++) {
            if (!B[A[i]]) {
                cnt++;
                B[A[i]] = true;
            }

            if (cnt == X) return i;
        }

        return -1;
    }
}
