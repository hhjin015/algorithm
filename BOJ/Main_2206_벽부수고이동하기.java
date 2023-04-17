package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2206_벽부수고이동하기 {
    static int N, M;
    static int ans = Integer.MAX_VALUE;
    static int[][] map, visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static class Pair {
        int x;
        int y;
        int dis;
        int crash;

        public Pair(int x, int y, int dis, int crash) {
            this.x = x;
            this.y = y;
            this.dis = dis;
            this.crash = crash;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
                visited[i][j] = Integer.MAX_VALUE;
            }
        }
        getDistance();

        if (ans == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);
    }

    private static void getDistance() {
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(0, 0, 1, 0));

        while (!q.isEmpty()) {
            Pair p = q.poll();

            if (p.x == N - 1 && p.y == M - 1) {
                ans = p.dis;
                break;
            }

            for (int d = 0; d < 4; d++) {
                int nr = p.x + dr[d];
                int nc = p.y + dc[d];

                if (!isBoundary(nr, nc)) continue;
                if (visited[nr][nc] <= p.crash) continue;

                if (map[nr][nc] == 0) {
                    visited[nr][nc] = p.crash;
                    q.offer(new Pair(nr, nc, p.dis + 1, p.crash));
                } else {
                    if (p.crash == 0) {
                        visited[nr][nc] = p.crash + 1;
                        q.offer(new Pair(nr, nc, p.dis + 1, p.crash + 1));
                    }
                }
            }
        }
    }

    private static boolean isBoundary(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
}
