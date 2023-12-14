package Programmers;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_level1_덧칠하기 {
    public static void main(String[] args) {
        System.out.println(solution(8, 4, new int[]{2, 3, 6}));
        System.out.println(solution(5, 4, new int[]{1, 3}));
        System.out.println(solution(4, 1, new int[]{1, 2, 3, 4}));
    }

    private static int solution(int n, int m, int[] section) {
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < section.length; i++) {
            q.offer(section[i]);
        }

        int answer = 0;
        int now = 0;

        while (!q.isEmpty()) {
            int p = q.poll();
            if (now >= p) continue;

            answer++;
            now = p + m - 1;
        }

        return answer;
    }
}
