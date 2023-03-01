package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_13565_침투_BFS {
    static int M, N, map[][];
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean visited[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        visited = new boolean[M][N];

        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            if (map[0][i] == 0)
                bfs(0, i);
        }

        System.out.println("NO");
    }

    private static void bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();

        q.offer(new int[]{r, c});
        visited[r][c] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];

                if (nr < 0 || nr >= M || nc < 0 || nc >= N || map[nr][nc] == 1 || visited[nr][nc]) continue;

                visited[nr][nc] = true;
                q.offer(new int[]{nr, nc});

                if (nr == M - 1) {
                    System.out.println("YES");
                    System.exit(0);
                }
            }
        }
    }
}

