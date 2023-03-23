package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14503_로봇청소기 {
    static int N, M, r, c, d, cleanCnt;
    static boolean flag;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (!flag) {
            if (map[r][c] == 0) {
                map[r][c] = 2;
                cleanCnt++;
            }

            int emptyCnt = 0;
            for (int dir = 0; dir < 4; dir++) {
                int nr = r + dr[dir];
                int nc = c + dc[dir];

                if (!isBoundary(nr, nc)) continue;
                if (map[nr][nc] == 0) emptyCnt++;
            }
            if (emptyCnt == 0) hasNotEmptySpace();
            else if (emptyCnt > 0) hasEmptySpace();

        }
        System.out.println(cleanCnt);
    }

    private static boolean isBoundary(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }

    private static void hasNotEmptySpace() {
        int back = (d + 2) % 4;
        int nr = r + dr[back];
        int nc = c + dc[back];
        if (isBoundary(nr, nc) && map[nr][nc] == 1) {
            flag = true;
        } else {
            r = nr;
            c = nc;
        }
    }

    private static void hasEmptySpace() {
        d = (d + 3) % 4;
        int nr = r + dr[d];
        int nc = c + dc[d];
        if (map[nr][nc] == 0) {
            r = nr;
            c = nc;
        }
    }
}

