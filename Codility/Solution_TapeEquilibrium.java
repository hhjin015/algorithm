package Codility;

public class Solution_TapeEquilibrium {
    public static void main(String[] args) {
        System.out.println(Solution(new int[]{3, 1, 2, 4, 3}));
    }

    private static int Solution(int[] A) {
        int min = Integer.MAX_VALUE;

        int firstPart = 0;
        int secondPart;
        int sum = 0;

        for (int i : A) {
            sum += i;
        }

        for (int i = 0; i < A.length - 1; i++) {
            firstPart += A[i];
            secondPart = sum - firstPart;

            int abs = Math.abs(firstPart - secondPart);
            min = Math.min(abs, min);
        }

        return min;
    }
}
