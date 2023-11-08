package Programmers;

import java.util.Arrays;

public class Solution_level1_과일장수 {
    public static void main(String[] args) {
        System.out.println(Solution(3, 4, new int[]{1, 2, 3, 1, 2, 3, 1}));
        System.out.println(Solution(4, 3, new int[]{4, 1, 2, 2, 4, 4, 4, 4, 1, 2, 4, 2}));
    }

    private static int Solution(int k, int m, int[] score) {
        Arrays.sort(score);

        int ans = 0;
        for (int i = score.length - 1; i >= 0; i -= m) {
            if (i - m < -1) break;
            ans += score[i - m + 1] * m;
        }

        return ans;
    }
}
