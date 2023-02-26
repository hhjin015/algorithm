package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2636_치즈 {
    static int N, M;
    static int[][] cheese;
    static boolean[][] air;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static Queue<Cheese> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cheese = new int[N][M];
        air = new boolean[N][M];
        q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        findOutsideAir(0, 0);
        int time = 0;
        int lastCheeseCount = 0;

        while (true) {
            int count = 0;
            for (int i = 1; i < N - 1; i++) {
                for (int j = 1; j < M - 1; j++) {
                    if (cheese[i][j] == 1) {
                        q.add(new Cheese(i, j));
                        count++;
                    }
                }
            }

            if (count == 0) {
                System.out.println(time);
                System.out.println(lastCheeseCount);
                break;
            }

            melting();
            changeAir();

            for (boolean[] b : air) {
                Arrays.fill(b, false);
            }

            findOutsideAir(0, 0);

            time++;
            lastCheeseCount = count;
        }
    }

    static class Cheese {
        int x;
        int y;

        public Cheese(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void changeAir() {
        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < M - 1; j++) {
                if (air[i][j]) {
                    cheese[i][j] = 0;
                }
            }
        }
    }

    private static void melting() {
        while (!q.isEmpty()) {
            Cheese c = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = c.x + dr[d];
                int nc = c.y + dc[d];

                if (cheese[nr][nc] == 0 && air[nr][nc]) {
                    air[c.x][c.y] = true;
                    break;
                }
            }
        }
    }

    private static void findOutsideAir(int r, int c) {
        air[r][c] = true;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (isBoundary(nr, nc) && cheese[nr][nc] == 0 && !air[nr][nc]) {
                findOutsideAir(nr, nc);
            }
        }
    }

    private static boolean isBoundary(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
}
