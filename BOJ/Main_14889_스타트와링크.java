package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_14889_스타트와링크 {
    static int N;
    static int[][] map;
    static boolean[] visit;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visit = new boolean[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combination(0, 0);
        System.out.println(min);
    }

    private static void combination(int cnt, int start) {
        if (cnt == N / 2) {
            calculateDiff();
            return;
        }

        for (int i = start; i < N; i++) {
            if (!visit[i]) {
                visit[i] = true;
                combination(cnt + 1, i + 1);
                visit[i] = false;
            }
        }
    }

    private static void calculateDiff() {
        int teamStart = 0;
        int teamLink = 0;

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (visit[i] && visit[j]) {
                    teamStart += map[i][j];
                    teamStart += map[j][i];
                } else if (!visit[i] && !visit[j]) {
                    teamLink += map[i][j];
                    teamLink += map[j][i];
                }
            }
        }

        int diff = Math.abs(teamStart - teamLink);
        min = Math.min(diff, min);

        if (min == 0) {
            System.out.println(min);
            System.exit(0);
        }
    }
}
