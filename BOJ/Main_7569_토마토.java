package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_7569_토마토 {
    static int M, N, H;
    static int[][][] map;
    // 상 우 하 좌 위 아래
    static int[] dx = {-1, 0, 1, 0, 0, 0};
    static int[] dy = {0, 1, 0, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, -1, 1};
    static Queue<Tomato> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());    // 가로
        N = Integer.parseInt(st.nextToken());    // 세로
        H = Integer.parseInt(st.nextToken());    // 높이
        map = new int[H][N][M];

        q = new LinkedList<>();
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                    if (map[i][j][k] == 1) {
                        q.add(new Tomato(i, j, k));
                    }
                }
            }
        }
        bfs();
    }

    static class Tomato {
        int x;
        int y;
        int z;

        public Tomato(int z, int x, int y) {
            this.z = z;
            this.x = x;
            this.y = y;
        }
    }

    private static void bfs() {
        while (!q.isEmpty()) {
            Tomato t = q.poll();

            for (int d = 0; d < 6; d++) {
                int nz = t.z + dz[d];
                int nx = t.x + dx[d];
                int ny = t.y + dy[d];

                if (isBoundary(nz, nx, ny) && map[nz][nx][ny] == 0) {
                    map[nz][nx][ny] = map[t.z][t.x][t.y] + 1;
                    q.add(new Tomato(nz, nx, ny));
                }
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if (map[i][j][k] == 0) {
                        System.out.println(-1);
                        return;
                    }
                    max = Math.max(max, map[i][j][k]);
                }
            }
        }
        if (max == 1) System.out.println(0);
        else System.out.println(max - 1);
    }

    private static boolean isBoundary(int z, int x, int y) {
        return x >= 0 && y >= 0 && z >= 0 && x < N && y < M && z < H;
    }
}
