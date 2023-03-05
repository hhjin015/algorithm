package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17070_파이프옮기기1 {
    static int N;
    static int[][] map;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        ans = 0;
        DFS(0, 1, 0);

        System.out.println(ans);
    }

    // 0: 가로 방향, 1: 세로 방향, 2: 대각선 방향.
    public static void DFS(int x, int y, int dir) {
        if (x == N - 1 && y == N - 1) {
            ans++;
            return;
        }

        //가로일 떄
        if (dir == 0) {
            //가로
            if (isBoundary(x, y + 1) && map[x][y + 1] == 0) DFS(x, y + 1, 0);
            //대각선
            if (isBoundary(x + 1, y + 1) && map[x][y + 1] == 0 && map[x + 1][y + 1] == 0 && map[x + 1][y] == 0)
                DFS(x + 1, y + 1, 2);
        }

        //세로일 때
        else if (dir == 1) {
            //세로
            if (isBoundary(x + 1, y) && map[x + 1][y] == 0) DFS(x + 1, y, 1);

            //대각선
            if (isBoundary(x + 1, y + 1) && map[x][y + 1] == 0 && map[x + 1][y + 1] == 0 && map[x + 1][y] == 0)
                DFS(x + 1, y + 1, 2);
        }

        //대각선일때
        else if (dir == 2) {
            //가로
            if (isBoundary(x, y + 1) && map[x][y + 1] == 0) DFS(x, y + 1, 0);

            //세로
            if (isBoundary(x + 1, y) && map[x + 1][y] == 0) DFS(x + 1, y, 1);

            //대각선
            if (isBoundary(x + 1, y + 1) && map[x][y + 1] == 0 && map[x + 1][y + 1] == 0 && map[x + 1][y] == 0)
                DFS(x + 1, y + 1, 2);
        }
    }

    private static boolean isBoundary(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
