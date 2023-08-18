package Programmers;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_2022_KAKAO_TECH_INTERNSHIP_level2_두큐합같게만들기 {
    public static void main(String[] args) {
        System.out.println(Solution(new int[]{3, 2, 7, 2}, new int[]{4, 6, 5, 1}));
        System.out.println(Solution(new int[]{1, 2, 1, 2}, new int[]{1, 10, 1, 2}));
        System.out.println(Solution(new int[]{1, 1}, new int[]{1, 5}));
    }

    private static int Solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        long sum1 = 0;
        long sum2 = 0;
        for (int i = 0; i < queue1.length; i++) {
            sum1 += queue1[i];
            sum2 += queue2[i];

            q1.offer(queue1[i]);
            q2.offer(queue2[i]);
        }
        if ((sum1 + sum2) % 2 == 1) return -1;

        int ans = 0;
        while (sum1 != sum2) {
            if (sum1 > sum2) {
                q2.offer(q1.peek());
                sum1 -= q1.peek();
                sum2 += q1.poll();
            } else {
                q1.offer(q2.peek());
                sum2 -= q2.peek();
                sum1 += q2.poll();
            }
            ++ans;

            if (ans > (queue1.length + queue2.length) * 2) return -1;
        }

        return ans;
    }
}
