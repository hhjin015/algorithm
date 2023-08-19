package Programmers;

import java.util.*;

public class Solution_2023_KAKAO_BLIND_RECRUITMENT_level3_미로탈출명령어 {
    static int[] dr = {1, 0, 0, -1};
    static int[] dc = {0, -1, 1, 0};
    static String[] dirs = {"d", "l", "r", "u"};

    static class Info implements Comparable<Info> {
        int r, c;
        int move;
        String orders;

        public Info(int r, int c, int move, String orders) {
            this.r = r;
            this.c = c;
            this.move = move;
            this.orders = orders;
        }

        @Override
        public int compareTo(Info o) {
            return this.orders.compareTo(o.orders);
        }
    }

    public static void main(String[] args) {
        System.out.println(Solution(3, 4, 2, 3, 3, 1, 5));
        System.out.println(Solution(2, 2, 1, 1, 2, 2, 2));
        System.out.println(Solution(3, 3, 1, 2, 3, 3, 4));
    }

    private static String Solution(int n, int m, int x, int y, int r, int c, int k) {
        PriorityQueue<Info> pq = new PriorityQueue<>();
        pq.offer(new Info(x - 1, y - 1, 0, ""));

        while (!pq.isEmpty()) {
            Info info = pq.poll();

            if (info.move == k && info.r == r - 1 && info.c == c - 1) return info.orders;
            if (info.move > k) continue;
            if (!isPossible(info.r, info.c, r - 1, c - 1, k - info.move)) continue;

            for (int d = 0; d < 4; d++) {
                int nr = info.r + dr[d];
                int nc = info.c + dc[d];

                if (isOutOfBounds(nr, nc, n, m)) continue;
                String next = info.orders + dirs[d];

                if (info.move < k) {
                    pq.add(new Info(nr, nc, info.move + 1, next));
                }
            }
        }

        return "impossible";
    }

    private static boolean isPossible(int x, int y, int r, int c, int move) {
        int rest = Math.abs(r - x) + Math.abs(c - y);

        if (rest > move) return false;
        if ((rest % 2) - (move % 2) == 0) return true;
        return false;
    }

    private static boolean isOutOfBounds(int r, int c, int n, int m) {
        return r < 0 || c < 0 || r >= n || c >= m;
    }
}
