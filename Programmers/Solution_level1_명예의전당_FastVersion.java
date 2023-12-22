package Programmers;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution_level1_명예의전당_FastVersion {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(3, new int[]{10, 100, 20, 150, 1, 100, 200})));
        System.out.println(Arrays.toString(solution(4, new int[]{0, 300, 40, 300, 20, 70, 150, 50, 500, 1000})));
    }

    private static int[] solution(int k, int[] score) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int[] answer = new int[score.length];

        if (k >= score.length) {
            for (int i = 0; i < score.length; i++) {
                pq.offer(score[i]);
                answer[i] = pq.peek();
            }
            return answer;
        }

        for (int i = 0; i < k; i++) {
            pq.offer(score[i]);
            answer[i] = pq.peek();
        }

        for (int i = k; i < score.length; i++) {
            if (pq.peek() < score[i]) {
                pq.offer(score[i]);
                pq.poll();
            }

            answer[i] = pq.peek();
        }

        return answer;
    }
}
