package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_15683_감시 {
    static int N, M;
    static int ans = Integer.MAX_VALUE;
    static int[][] map, copyMap;
    static int[] directions;
    static List<CCTV> cctvList;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static class CCTV {
        int r;
        int c;
        int num;

        public CCTV(int r, int c, int num) {
            this.r = r;
            this.c = c;
            this.num = num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        copyMap = new int[N][M];
        cctvList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0 && map[i][j] != 6) {
                    cctvList.add(new CCTV(i, j, map[i][j]));
                }
            }
        }

        directions = new int[cctvList.size()];
        permutation(0);
        System.out.println(ans);
    }

    private static void permutation(int cnt) {
        if (cnt == cctvList.size()) {
            for (int i = 0; i < N; i++) {
                copyMap[i] = map[i].clone();
            }

            for (int i = 0; i < cctvList.size(); i++) {
                int d = directions[i];
                CCTV c = cctvList.get(i);
                instruct(d, c);
            }

            getBlindSpot();
            return;
        }

        for (int i = 0; i < 4; i++) {
            directions[cnt] = i;
            permutation(cnt + 1);
        }
    }

    private static void instruct(int d, CCTV cctv) {
        int num = cctv.num;
        if (num == 1) watch(d, cctv);
        else if (num == 2) {
            if (d == 0 || d == 2) {
                watch(0, cctv);
                watch(2, cctv);
            } else {
                watch(1, cctv);
                watch(3, cctv);
            }
        } else if (num == 3) {
            if (d == 3) {
                watch(3, cctv);
                watch(0, cctv);
            } else {
                watch(d, cctv);
                watch(d + 1, cctv);
            }
        } else if (num == 4) {
            if (d == 0 || d == 1) {
                watch(d, cctv);
                watch(d + 1, cctv);
                watch(d + 2, cctv);
            } else if (d == 2) {
                watch(2, cctv);
                watch(3, cctv);
                watch(0, cctv);
            } else {
                watch(3, cctv);
                watch(0, cctv);
                watch(1, cctv);
            }
        } else if (num == 5) {
            watch(0, cctv);
            watch(1, cctv);
            watch(2, cctv);
            watch(3, cctv);
        }
    }

    private static void watch(int d, CCTV cctv) {
        int r = cctv.r;
        int c = cctv.c;

        while (true) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (!isBoundary(nr, nc) || map[nr][nc] == 6) break;

            copyMap[nr][nc] = -1;
            r = nr;
            c = nc;
        }
    }

    private static void getBlindSpot() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copyMap[i][j] == 0) {
                    cnt++;
                }
            }
        }
        ans = Math.min(ans, cnt);
    }

    private static boolean isBoundary(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }
}
