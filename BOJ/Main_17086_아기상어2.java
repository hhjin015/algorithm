package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_17086_아기상어2 {
    static int N, M, ans;
    static int[][] map, distance;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    static Queue<Shark> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        distance = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    q.offer(new Shark(i, j));
                }
            }
        }

        ans = Integer.MIN_VALUE;
        bfs();
        System.out.println(ans);
    }

    static class Shark {
        int r;
        int c;

        public Shark(int r, int c) {
            super();
            this.r = r;
            this.c = c;
        }
    }

    private static void bfs() {
        while (!q.isEmpty()) {
            Shark shark = q.poll();
            int r = shark.r;
            int c = shark.c;
            for (int d = 0; d < 8; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (!isBoundary(nr, nc)) continue;
                if (distance[nr][nc] != 0 || map[nr][nc] == 1) continue;
                distance[nr][nc] = distance[r][c] + 1;
                ans = Math.max(ans, distance[nr][nc]);
                q.offer(new Shark(nr, nc));
            }
        }
    }

    private static boolean isBoundary(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
}
