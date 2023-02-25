package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2573_빙산 {
    static int N, M;
    static int[][] map;
    static boolean[][] visit;
    static Queue<Ice> q;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        q = new LinkedList<>();
        int year = 0;
        while (true) {
            int num = getNumberOfIce();
            if (num >= 2) {
                System.out.println(year);
                break;
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == 0) {
                        q.add(new Ice(i, j));
                    }
                }
            }

            if (q.size() == N * M) {
                System.out.println("0");
                break;
            }

            melt();
            year++;
        }
    }

    static class Ice {
        int x;
        int y;

        public Ice(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void melt() {
        while (!q.isEmpty()) {
            Ice ice = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = ice.x + dr[d];
                int nc = ice.y + dc[d];

                if (isBoundary(nr, nc) && map[nr][nc] > 0) {
                    map[nr][nc] = map[nr][nc] - 1;
                }
            }
        }
    }

    private static int getNumberOfIce() {
        int num = 0;
        visit = new boolean[N][M];

        for (int i = 1; i < N - 1; i++) {
            for (int j = 1; j < M - 1; j++) {
                if (map[i][j] != 0 && !visit[i][j]) {
                    dfs(i, j);
                    num++;
                }
            }
        }
        return num;
    }

    private static void dfs(int r, int c) {
        visit[r][c] = true;
        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (isBoundary(nr, nc) && !visit[nr][nc] && map[nr][nc] > 0) {
                dfs(nr, nc);
            }
        }
    }

    private static boolean isBoundary(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
}
