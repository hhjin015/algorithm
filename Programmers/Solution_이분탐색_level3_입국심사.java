package Programmers;

import java.util.Arrays;

public class Solution_이분탐색_level3_입국심사 {
    public static void main(String[] args) {
        System.out.println(Solution(6, new int[]{7, 10}));
    }

    private static long Solution(int n, int[] times) {
        Arrays.sort(times);
        long answer = 0;
        long left = 0;
        long right = times[times.length - 1] * (long) n;

        while (left <= right) {
            long mid = (left + right) / 2;
            long complete = 0;

            for (int i = 0; i < times.length; i++) {
                complete += mid / times[i];
            }

            if (complete < n)
                left = mid + 1;
            else {
                answer = mid;
                right = mid - 1;
            }
        }

        return answer;
    }
}
