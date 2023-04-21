package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_14500_테트로미노 {
    static int N, M;
    static int max = Integer.MIN_VALUE;
    static int[][] map;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dfs(i, j, 0, 0);
                exception(i, j);
            }
        }
        System.out.println(max);
    }

    private static void dfs(int r, int c, int depth, int sum) {
        if (depth == 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (isOutOfRange(nr, nc) || visited[nr][nc]) continue;

            visited[nr][nc] = true;
            dfs(nr, nc, depth + 1, sum + map[nr][nc]);
            visited[nr][nc] = false;
        }
    }

    private static void exception(int r, int c) {
        int cnt = 1;
        List<Integer> list = new ArrayList<>();

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (isOutOfRange(nr, nc)) continue;

            cnt++;
            list.add(map[nr][nc]);
        }

        if (cnt < 4) return;
        int sum = 0;
        if (cnt == 4) sum = map[r][c] + list.get(0) + list.get(1) + list.get(2);
        else if (cnt == 5) {
            Collections.sort(list);
            sum = map[r][c] + list.get(1) + list.get(2) + list.get(3);
        }
        max = Math.max(max, sum);
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= M;
    }
}
