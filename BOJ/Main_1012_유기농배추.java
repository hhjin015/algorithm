package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1012_유기농배추 {
    static int T, K, M, N;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[M][N];
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());

                map[X][Y] = 1;
            }

            int ans = 0;
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 1) {
                        dfs(i, j);
                        ans++;
                    }
                }
            }
            sb.append(ans).append("\n");
        } //end tc
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb);
    }

    private static void dfs(int x, int y) {
        map[x][y] = 0;
        for (int d = 0; d < 4; d++) {
            int nr = x + dr[d];
            int nc = y + dc[d];

            if (isBoundary(nr, nc) && map[nr][nc] == 1) {
                dfs(nr, nc);
            }
        }
    }

    private static boolean isBoundary(int x, int y) {
        return x >= 0 && y >= 0 && x < M && y < N;
    }
}
