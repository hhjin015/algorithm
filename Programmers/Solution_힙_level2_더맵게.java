package Programmers;

import java.util.PriorityQueue;

public class Solution_힙_level2_더맵게 {
    public static void main(String[] args) {
        System.out.println(Solution(new int[]{1, 2, 3, 9, 10, 12}, 7));
    }

    private static int Solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < scoville.length; i++) {
            pq.add(scoville[i]);
        }

        int answer = 0;
        while (true) {
            if (pq.peek() >= K) break;
            else if (pq.size() <= 1) {
                answer = -1;
                break;
            }
            int first = pq.poll();
            int second = pq.poll();

            int mix = first + (second * 2);
            pq.add(mix);

            answer++;
        }
        return answer;
    }
}
