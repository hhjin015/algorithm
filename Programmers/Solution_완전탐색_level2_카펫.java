package Programmers;

import java.util.Arrays;

public class Solution_완전탐색_level2_카펫 {
    public static void main(String[] args) {
        int brown = 24;
        int yellow = 24;

        int[] answer = getAnswer(brown, yellow);
        System.out.println(Arrays.toString(answer));
    }

    private static int[] getAnswer(int b, int y) {
        int[] arr = new int[2];
        int num = b + y;

        for (int i = 3; i <= num / 3; i++) {
            if (num % i == 0) {
                arr[0] = num / i;   // 가로
                arr[1] = i;         // 세로

                if (b == getNum(arr)) return arr;
            }
        }
        return arr;
    }

    private static int getNum(int[] arr) {
        return (arr[0] * 2) + (arr[1] - 2) * 2;
    }
}
