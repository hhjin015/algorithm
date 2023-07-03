package Programmers;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_깊이너비우선탐색_level2_게임맵최단거리 {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int N, M;

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        int[][] map = {{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}};
        System.out.println(Solution(map));
    }

    private static int Solution(int[][] maps) {
        N = maps.length;
        M = maps[0].length;

        if (maps[N - 1][M - 1] == 0) return -1;
        return bfs(maps);
    }

    private static int bfs(int[][] maps) {
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(0, 0));

        while (!q.isEmpty()) {
            Pair p = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = p.x + dr[d];
                int nc = p.y + dc[d];

                if (isOutOfRange(nr, nc) || maps[nr][nc] != 1) continue;

                maps[nr][nc] = maps[p.x][p.y] + 1;
                q.offer(new Pair(nr, nc));

                if (nr == N - 1 && nc == M - 1) return maps[nr][nc];
            }
        }

        return -1;
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= M;
    }
}
