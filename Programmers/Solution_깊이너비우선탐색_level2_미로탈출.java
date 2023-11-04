package Programmers;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_깊이너비우선탐색_level2_미로탈출 {
    static class Loc {
        int r, c, dis;

        public Loc(int r, int c, int dis) {
            this.r = r;
            this.c = c;
            this.dis = dis;
        }
    }

    static char[][] arr;
    static int rSize, cSize;

    public static void main(String[] args) {
        System.out.println(Solution(new String[]{"SOOOL", "XXXXO", "OOOOO", "OXXXX", "OOOOE"}));
        System.out.println(Solution(new String[]{"LOOXS", "OOOOX", "OOOOO", "OOOOO", "EOOOO"}));
    }

    private static int Solution(String[] maps) {
        rSize = maps.length;
        cSize = maps[0].length();
        arr = new char[rSize][cSize];

        for (int i = 0; i < rSize; i++) {
            arr[i] = maps[i].toCharArray();
        }

        int ans = 0;
        for (int i = 0; i < rSize; i++) {
            for (int j = 0; j < cSize; j++) {
                if (arr[i][j] == 'S') {
                    int cnt = bfs(new Loc(i, j, 0), 'L');
                    if (cnt == -1) return -1;
                    else ans += cnt;
                } else if (arr[i][j] == 'L') {
                    int cnt = bfs(new Loc(i, j, 0), 'E');
                    if (cnt == -1) return -1;
                    else ans += cnt;
                }
            }
        }

        return ans;
    }

    private static int bfs(Loc start, char target) {
        Queue<Loc> q = new LinkedList<>();
        boolean[][] visited = new boolean[rSize][cSize];
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        q.offer(start);
        visited[start.r][start.c] = true;

        while (!q.isEmpty()) {
            Loc p = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = p.r + dr[d];
                int nc = p.c + dc[d];

                if (isOutOfBounds(nr, nc) || visited[nr][nc] || arr[nr][nc] == 'X') continue;
                if (arr[nr][nc] == target) return p.dis + 1;

                q.offer(new Loc(nr, nc, p.dis + 1));
                visited[nr][nc] = true;
            }

        }
        return -1;
    }

    private static boolean isOutOfBounds(int r, int c) {
        return r < 0 || c < 0 || r >= rSize || c >= cSize;
    }
}
