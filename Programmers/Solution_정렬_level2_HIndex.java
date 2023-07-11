package Programmers;

import java.util.Arrays;

public class Solution_정렬_level2_HIndex {
    public static void main(String[] args) {
        System.out.println(Solution(new int[]{3, 0, 6, 1, 5}));
    }

    private static int Solution(int[] citations) {
        int answer = 0;
        Arrays.sort(citations);

        for (int i = 0; i < citations.length; i++) {
            int h = citations.length - i;

            if (citations[i] >= h) {
                answer = h;
                break;
            }
        }

        return answer;
    }
}
