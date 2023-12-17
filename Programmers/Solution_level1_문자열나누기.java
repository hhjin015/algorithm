package Programmers;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_level1_문자열나누기 {
    public static void main(String[] args) {
        System.out.println(solution("banana"));
        System.out.println(solution("abracadabra"));
        System.out.println(solution("aaabbaccccabba"));
    }

    private static int solution(String s) {
        int answer = 0;
        Queue<Character> q = new LinkedList<>();

        for (int i = 0; i < s.length(); i++) {
            q.offer(s.charAt(i));
        }

        char x = q.peek();
        int same = 0;
        int diff = 0;

        while (!q.isEmpty()) {
            char p = q.poll();

            if (x == p) same++;
            else diff++;

            if (same == diff) {
                answer++;
                same = 0;
                diff = 0;

                if (!q.isEmpty()) x = q.peek();
            } else {
                if (q.isEmpty()) answer++;
            }
        }

        return answer;
    }
}
