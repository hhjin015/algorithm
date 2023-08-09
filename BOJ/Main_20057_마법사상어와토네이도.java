package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_20057_마법사상어와토네이도 {
    static int N, dir, ans;
    static int[][] map;

    // 좌 하 우 상
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {-1, 0, 1, 0};

    static int[] dr0 = {-1, -1, -1, 1, 1, 1, -2, 2, 0};
    static int[] dc0 = {1, 0, -1, -1, 0, 1, 0, 0, -2};

    static int[] dr1 = {-1, 0, 1, 1, 0, -1, 0, 0, 2};
    static int[] dc1 = {-1, -1, -1, 1, 1, 1, -2, 2, 0};

    static int[] dr2 = {1, 1, 1, -1, -1, -1, 2, -2, 0};
    static int[] dc2 = {-1, 0, 1, 1, 0, -1, 0, 0, 2};

    static int[] dr3 = {1, 0, -1, -1, 0, 1, 0, 0, -2};
    static int[] dc3 = {1, 1, 1, -1, -1, -1, 2, -2, 0};

    static int[] percentage = {1, 7, 10, 10, 7, 1, 2, 2, 5};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int r = N / 2;
        int c = N / 2;
        int t = 1;

        loop:
        while (true) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < t; j++) {
                    int nr = r + dr[dir];
                    int nc = c + dc[dir];

                    if (map[nr][nc] > 0) {
                        int alpha = spreadSandAndGetAlpha(nr, nc);
                        int alphaIdxR = nr + dr[dir];
                        int alphaIdxC = nc + dc[dir];

                        if (isOutOfRange(alphaIdxR, alphaIdxC)) ans += alpha;
                        else map[alphaIdxR][alphaIdxC] += alpha;
                    }

                    r = nr;
                    c = nc;
                    map[r][c] = 0;

                    if (r == 0 && c == 0) break loop;
                }
                dir = (dir + 1) % 4;
            }
            ++t;
        }

        System.out.println(ans);
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= N;
    }

    private static int spreadSandAndGetAlpha(int r, int c) {
        int total = 0;
        int nr = 0;
        int nc = 0;

        for (int i = 0; i < 9; i++) {
            int sand = (int) ((map[r][c] * percentage[i]) * 0.01);
            if (sand < 1) continue;

            switch (dir) {
                case 0:
                    nr = r + dr0[i];
                    nc = c + dc0[i];
                    break;
                case 1:
                    nr = r + dr1[i];
                    nc = c + dc1[i];
                    break;
                case 2:
                    nr = r + dr2[i];
                    nc = c + dc2[i];
                    break;
                case 3:
                    nr = r + dr3[i];
                    nc = c + dc3[i];
                    break;
            }
            if (isOutOfRange(nr, nc)) ans += sand;
            else map[nr][nc] += sand;

            total += sand;
        }
        return map[r][c] - total;
    }
}
