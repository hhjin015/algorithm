package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_14890_경사로 {
    static int N, L, ans;
    static int[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        visited = new boolean[N][N];

        // 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 행 탐색
        for (int i = 0; i < N; i++) {
            boolean flag = true;
            for (int j = 0; j < N - 1; j++) {
                if (map[i][j] == map[i][j + 1]) continue;
                else if (map[i][j] + 1 == map[i][j + 1]) flag = checkBack(i, j, 0);
                else if (map[i][j] == map[i][j + 1] + 1) flag = checkNext(i, j, 0);
                else flag = false;
                if (!flag) break;
            }
            if (flag) ans++;
        }

        for (boolean[] b : visited) {
            Arrays.fill(b, false);
        }

        // 열 탐색
        for (int j = 0; j < N; j++) {
            boolean flag = true;
            for (int i = 0; i < N - 1; i++) {
                if (map[i][j] == map[i + 1][j]) continue;
                else if (map[i][j] + 1 == map[i + 1][j]) flag = checkBack(i, j, 1);
                else if (map[i][j] == map[i + 1][j] + 1) flag = checkNext(i, j, 1);
                else flag = false;
                if (!flag) break;
            }
            if (flag) ans++;
        }
        System.out.println(ans);
    }

    private static boolean checkNext(int i, int j, int v) {
        int num = map[i][j] - 1;

        if (v == 0) {
            for (int k = 1; k <= L; k++) {
                if (!isBoundary(i, j + k) || map[i][j + k] != num) return false;
            }
            for (int k = 1; k <= L; k++) {
                visited[i][j + k] = true;
            }
        } else {
            for (int k = 1; k <= L; k++) {
                if (!isBoundary(i + k, j) || map[i + k][j] != num) return false;
            }
            for (int k = 1; k <= L; k++) {
                visited[i + k][j] = true;
            }
        }
        return true;
    }

    private static boolean checkBack(int i, int j, int v) {
        int num = map[i][j];
        if (visited[i][j]) return false;
        if (v == 0) {
            for (int k = 1; k < L; k++) {
                if (!isBoundary(i, j - k) || map[i][j - k] != num || visited[i][j - k]) return false;
            }
        } else {
            for (int k = 1; k < L; k++) {
                if (!isBoundary(i - k, j) || map[i - k][j] != num || visited[i - k][j]) return false;
            }
        }
        return true;
    }

    private static boolean isBoundary(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
