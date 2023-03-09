package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_17144_미세먼지안녕 {
    static int R, C;
    static int[][] original, copy;
    static Queue<Pair> q = new LinkedList<>();
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        original = new int[R][C];
        copy = new int[R][C];
        List<Pair> cleaner = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                original[i][j] = Integer.parseInt(st.nextToken());
                copy[i][j] = original[i][j];
                if (original[i][j] == -1) {
                    cleaner.add(new Pair(i, j));
                }
            }
        }

        while (T > 0) {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (original[i][j] > 0) q.add(new Pair(i, j));
                }
            }

            spread();
            cleaning(cleaner.get(0).x, cleaner.get(0).y + 1, new int[]{0, -1, 0, 1}, new int[]{1, 0, -1, 0});
            cleaning(cleaner.get(1).x, cleaner.get(1).y + 1, new int[]{0, 1, 0, -1}, new int[]{1, 0, -1, 0});

            for (int i = 0; i < original.length; i++) {
                copy[i] = original[i].clone();
            }
            T--;
        }

        int ans = 0;
        for (int[] a : original) {
            for (int aa : a) {
                ans += aa;
            }
        }

        System.out.println(ans + 2);
    }

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void spread() {
        while (!q.isEmpty()) {
            Pair p = q.poll();
            int cnt = 0;
            int spread = 0;

            for (int d = 0; d < 4; d++) {
                int nr = p.x + dr[d];
                int nc = p.y + dc[d];

                if (isBoundary(nr, nc) && original[nr][nc] != -1) {
                    spread = copy[p.x][p.y] / 5;
                    original[nr][nc] += spread;
                    cnt++;
                }
            }
            original[p.x][p.y] = original[p.x][p.y] - (spread * cnt);
        }
    }

    private static void cleaning(int r, int c, int[] dr, int[] dc) {
        int dir = 0;
        int me = original[r][c];
        original[r][c] = 0;
        int i = r;
        int j = c;

        while (true) {
            int nr = r + dr[dir];
            int nc = c + dc[dir];

            if (!isBoundary(nr, nc)) {
                dir = ++dir % 4;
            }
            r = r + dr[dir];
            c = c + dc[dir];

            if (r == i && c + 1 == j) break;

            int next = original[r][c];
            original[r][c] = me;
            me = next;
        }
    }

    private static boolean isBoundary(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }
}
