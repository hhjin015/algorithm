package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_21610_마법사상어와비바라기 {
    static int N, M, D, S;
    static int[][] map;
    static List<Cloud> clouds = new ArrayList<>();
    static boolean[][] visited;
    static int[] dr = {0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dc = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기 구름 세팅
        clouds.add(new Cloud(N - 2, 0));
        clouds.add(new Cloud(N - 2, 1));
        clouds.add(new Cloud(N - 1, 0));
        clouds.add(new Cloud(N - 1, 1));

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken()) - 1;
            S = Integer.parseInt(st.nextToken());

            moveCloud();
            checkDiagonal();
            makeCloud();
        }

        int ans = 0;
        for (int[] arr : map) {
            for (int a : arr) {
                ans += a;
            }
        }
        System.out.println(ans);
    }

    private static class Cloud {
        int x;
        int y;

        public Cloud(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void moveCloud() {
        for (Cloud c : clouds) {
            c.x = (N + c.x + dr[D] * (S % N)) % N;
            c.y = (N + c.y + dc[D] * (S % N)) % N;
            map[c.x][c.y]++;
        }
    }

    private static void checkDiagonal() {
        for (Cloud c : clouds) {
            int cnt = 0;
            visited[c.x][c.y] = true;

            for (int d = 1; d < 8; d += 2) {
                int nr = c.x + dr[d];
                int nc = c.y + dc[d];

                if (isBoundary(nr, nc) && map[nr][nc] > 0) cnt++;
            }
            map[c.x][c.y] += cnt;
        }
        clouds.clear();
    }

    private static void makeCloud() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] >= 2) {
                    clouds.add(new Cloud(i, j));
                    map[i][j] -= 2;
                }
            }
        }
        visited = new boolean[N][N];
    }

    private static boolean isBoundary(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
