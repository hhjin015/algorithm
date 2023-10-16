package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_1941_소문난칠공주 {
    static int[] inputs, numbers;
    static char[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        inputs = new int[25];
        numbers = new int[7];
        map = new char[5][5];

        for (int i = 0; i < 5; i++) {
            map[i] = br.readLine().toCharArray();
        }

        for (int i = 0; i < 25; i++) {
            inputs[i] = i;
        }

        combination(0, 0);

        System.out.println(ans);
    }

    private static void combination(int cnt, int start) {
        if (cnt == 7) {
            if (check() && bfs()) ++ans;
            return;
        }

        for (int i = start; i < 25; i++) {
            numbers[cnt] = inputs[i];
            combination(cnt + 1, i + 1);
        }
    }

    private static boolean check() {
        int cnt = 0;
        for (int i = 0; i < 7; i++) {
            if (map[numbers[i] / 5][numbers[i] % 5] == 'S') ++cnt;
        }

        return cnt >= 4;
    }

    private static boolean bfs() {
        Queue<int[]> q = new LinkedList<>();
        boolean[] visited = new boolean[7];
        visited[0] = true;
        int cnt = 1;
        q.offer(new int[]{numbers[0] / 5, numbers[0] % 5});

        while (!q.isEmpty()) {
            int[] p = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = p[0] + dr[d];
                int nc = p[1] + dc[d];

                if (isOutOfBounds(nr, nc)) continue;

                for (int i = 0; i < 7; i++) {
                    if (visited[i]) continue;
                    if (nr * 5 + nc == numbers[i]) {
                        ++cnt;
                        visited[i] = true;
                        q.offer(new int[]{nr, nc});
                        break;
                    }
                }
            }
        }

        return cnt == 7;
    }

    private static boolean isOutOfBounds(int r, int c) {
        return r < 0 || c < 0 || r >= 5 || c >= 5;
    }
}
