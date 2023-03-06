package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2638_치즈 {
    static int N, M, time;
    static int[][] map;
    static boolean[][] air;
    static Queue<Cheese> q;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        air = new boolean[N][M];
        q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        time = 0;

        while (true) {
            findOutsideAir(0, 0);

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 1) {
                        q.offer(new Cheese(i, j));
                    }
                }
            }

            if (q.size() == 0) break;

            melting();
            changeAir();

            for (boolean[] b : air) {
                Arrays.fill(b, false);
            }

            time++;
        }
        System.out.println(time);
    }

    static private class Cheese {
        int x;
        int y;

        public Cheese(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void findOutsideAir(int r, int c) {
        air[r][c] = true;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (isBoundary(nr, nc) && map[nr][nc] == 0 && !air[nr][nc]) {
                findOutsideAir(nr, nc);
            }
        }
    }

    private static void melting() {
        while (!q.isEmpty()) {
            Cheese c = q.poll();
            int cnt = 0;

            for (int d = 0; d < 4; d++) {
                int nr = c.x + dr[d];
                int nc = c.y + dc[d];

                if (isBoundary(nr, nc) && air[nr][nc] && map[nr][nc] == 0) cnt++;
            }
            if (cnt >= 2) air[c.x][c.y] = true;
        }
    }

    private static void changeAir() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (air[i][j]) map[i][j] = 0;
            }
        }
    }

    private static boolean isBoundary(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
}
