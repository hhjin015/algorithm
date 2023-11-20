package Programmers;

import java.util.Arrays;

public class Solution_2018_KAKAO_BLIND_RECRUITMENT_level1_비밀지도 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(Solution(5, new int[]{9, 20, 28, 18, 11}, new int[]{30, 1, 21, 17, 28})));
        System.out.println(Arrays.toString(Solution(6, new int[]{46, 33, 33, 22, 31, 50}, new int[]{27, 56, 19, 14, 14, 10})));
    }

    private static String[] Solution(int n, int[] arr1, int[] arr2) {
        int[][] forArr1 = new int[n][n];
        int[][] forArr2 = new int[n][n];

        for (int i = 0; i < n; i++) {
            int[] arr = new int[n];
            forArr1[i] = getBinary(arr1[i], n - 1, arr);
        }

        for (int i = 0; i < n; i++) {
            int[] arr = new int[n];
            forArr2[i] = getBinary(arr2[i], n - 1, arr);
        }

        String[] answer = new String[n];
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (forArr1[i][j] == 0 && forArr2[i][j] == 0) sb.append(" ");
                else sb.append("#");
            }
            answer[i] = sb.toString();
        }

        return answer;
    }

    private static int[] getBinary(int num, int d, int[] arr) {
        if (num == 0) return arr;

        arr[d] = num % 2;
        return getBinary(num / 2, d - 1, arr);
    }
}
