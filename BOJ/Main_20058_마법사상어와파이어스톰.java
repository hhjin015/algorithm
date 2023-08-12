package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_20058_마법사상어와파이어스톰 {
    static int N, Q, size, totalIce, land;
    static int[][] map, copyMap;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        size = (int) Math.pow(2, N);
        map = new int[size][size];
        copyMap = new int[size][size];
        int[] levels = new int[Q];

        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                int v = Integer.parseInt(st.nextToken());
                map[i][j] = v;
                copyMap[i][j] = v;
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            levels[i] = Integer.parseInt(st.nextToken());
        }


        for (int t = 0; t < Q; t++) {
            int pow = (int) Math.pow(2, levels[t]);

            for (int i = 0; i < size; i += pow) {
                for (int j = 0; j < size; j += pow) {
                    turnMap(i, j, pow);
                }
            }

            copy(copyMap, map);
            meltIce();
            copy(map, copyMap);
        }

        findBiggestLand();

        System.out.println(totalIce);
        System.out.println(land);
    }

    private static void copy(int[][] map1, int[][] map2) {
        for (int i = 0; i < size; i++) {
            map1[i] = map2[i].clone();
        }
    }

    private static void turnMap(int r, int c, int L) {
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                map[i + r][j + c] = copyMap[r + L - 1 - j][i + c];
            }
        }
    }

    private static void meltIce() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map[i][j] == 0) continue;

                int cnt = 0;
                for (int d = 0; d < 4; d++) {
                    int nr = i + dr[d];
                    int nc = j + dc[d];

                    if (isOutOfRange(nr, nc) || map[nr][nc] == 0) continue;
                    ++cnt;
                }

                if (cnt < 3) copyMap[i][j]--;
            }
        }
    }

    private static void findBiggestLand() {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                totalIce += map[i][j];

                if (map[i][j] == 0 || visited[i][j]) continue;

                q.offer(new int[]{i, j});
                visited[i][j] = true;
                int cnt = 1;

                while (!q.isEmpty()) {
                    int[] p = q.poll();

                    for (int d = 0; d < 4; d++) {
                        int nr = p[0] + dr[d];
                        int nc = p[1] + dc[d];

                        if (isOutOfRange(nr, nc) || visited[nr][nc] || map[nr][nc] == 0) continue;

                        q.offer(new int[]{nr, nc});
                        visited[nr][nc] = true;
                        cnt++;
                    }
                }

                land = Math.max(cnt, land);
            }
        }
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || c < 0 || r >= size || c >= size;
    }
}
