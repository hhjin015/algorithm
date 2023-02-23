package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2589_보물섬 {
    static int N, M;
    static int[][] map, copyMap;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        copyMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - 'L';
            }
        }

        setMap(map, copyMap);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    max = Math.max(max, bfs(i, j));
                    setMap(copyMap, map);
                }
            }
        }
        System.out.println(max - 1);
    }

    static class Land {
        int x;
        int y;

        public Land(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int bfs(int r, int c) {
        Queue<Land> q = new LinkedList<>();
        q.add(new Land(r, c));
        map[r][c] = 1;
        int max = Integer.MIN_VALUE;

        while (!q.isEmpty()) {
            Land land = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = land.x + dr[d];
                int nc = land.y + dc[d];

                if (isBoundary(nr, nc) && map[nr][nc] == 0) {
                    q.add(new Land(nr, nc));
                    map[nr][nc] = map[land.x][land.y] + 1;
                    max = Math.max(map[nr][nc], max);
                }
            }
        }
        return max;
    }

    private static boolean isBoundary(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }

    private static void setMap(int[][] map, int[][] copyMap) {
        for (int i = 0; i < map.length; i++) {
            copyMap[i] = map[i].clone();
        }
    }
}
