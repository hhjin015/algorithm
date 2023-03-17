package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1987_알파벳 {
    static int R, C, ans;
    static int[][] map;
    static boolean[] check = new boolean[26];
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j) - 'A';
            }
        }
        visited[0][0] = true;
        check[map[0][0]] = true;

        dfs(0, 0, 1);

        System.out.println(ans);
    }

    private static void dfs(int r, int c, int cnt) {
        ans = Math.max(ans, cnt);

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (isBoundary(nr, nc) && !visited[nr][nc] && !check[map[nr][nc]]) {
                visited[nr][nc] = true;
                check[map[nr][nc]] = true;
                dfs(nr, nc, cnt + 1);
                visited[nr][nc] = false;
            }
        }
        check[map[r][c]] = false;
    }

    private static boolean isBoundary(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }
}
