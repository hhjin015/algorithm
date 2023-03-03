package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_11559_PuyoPuyo {
    static int ans, cnt;
    static char[][] map = new char[12][6];
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 12; i++) {
            String str = br.readLine();
            map[i] = str.toCharArray();
        }

        ans = 0;
        while (true) {
            boolean flag = false;
            for (int i = 0; i < 12; i++) {
                for (int j = 0; j < 6; j++) {
                    if (map[i][j] != '.') {
                        char color = map[i][j];
                        cnt = 0;
                        checkPuyo(i, j, map[i][j]);
                        if (cnt < 4) {
                            rewind(color);
                        } else {
                            q.clear();
                            flag = true;
                        }
                    }
                }
            }
            if (flag) ans++;
            else break;

            gravity();
        }

        System.out.println(ans);
    }

    private static void checkPuyo(int r, int c, char color) {
        map[r][c] = '.';
        cnt++;
        q.add(new int[]{r, c});

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (isBoundary(nr, nc) && map[nr][nc] == color) {
                checkPuyo(nr, nc, map[nr][nc]);
            }
        }
    }

    private static void gravity() {
        for (int j = 0; j < 6; j++) {
            int n = 0;
            for (int i = 11; i > 0; i--) {
                if (map[i][j] == '.') {
                    n = i;
                    while (true) {
                        n--;
                        if (n < 0) break;
                        if (map[n][j] != '.') {
                            map[i][j] = map[n][j];
                            map[n][j] = '.';
                            break;
                        }
                    }
                }
                if (n < 0) break;
            }
        }
    }

    private static void rewind(char c) {
        while (!q.isEmpty()) {
            int[] arr = q.poll();
            map[arr[0]][arr[1]] = c;
        }
    }

    private static boolean isBoundary(int r, int c) {
        return r >= 0 && c >= 0 && r < 12 && c < 6;
    }
}
