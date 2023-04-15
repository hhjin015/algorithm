package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_10026_적록색약 {
    static int N, weaknessCnt, normalCnt;
    static char[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i] = str.toCharArray();
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    weakness(i, j);
                    weaknessCnt++;
                }
            }
        }

        for (boolean[] b : visited) {
            Arrays.fill(b, false);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    normal(i, j);
                    normalCnt++;
                }
            }
        }
        System.out.println(normalCnt + " " + weaknessCnt);
    }

    private static void normal(int r, int c) {
        visited[r][c] = true;
        char color = map[r][c];

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (!isBoundary(nr, nc) || visited[nr][nc] || map[nr][nc] != color) continue;
            normal(nr, nc);
        }
    }

    private static void weakness(int r, int c) {
        visited[r][c] = true;
        char color = map[r][c];

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (!isBoundary(nr, nc) || visited[nr][nc]) continue;
            if (color == 'B') {
                if (map[nr][nc] != color) continue;
            } else {
                if (map[nr][nc] == 'B') continue;
            }

            weakness(nr, nc);
        }
    }

    private static boolean isBoundary(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
