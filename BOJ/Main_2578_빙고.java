package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2578_빙고 {
    static int cnt;
    static int[][] map;
    static int[] moderator;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        map = new int[5][5];
        moderator = new int[25];

        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 5; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < 5; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = i * 5; j < i * 5 + 5; j++) {
                moderator[j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int t = 0; t < moderator.length; t++) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (map[i][j] == moderator[t]) {
                        cnt = 0;
                        map[i][j] = 0;
                        rCheck();
                        cCheck();
                        diagonalCheck();
                        if (cnt >= 3) {
                            System.out.println(t + 1);
                            System.exit(0);
                        }
                    }
                }
            }
        }
    }

    private static void rCheck() {
        for (int i = 0; i < 5; i++) {
            int count = 0;
            for (int j = 0; j < 5; j++) {
                if (map[i][j] == 0) count++;
            }
            if (count == 5) cnt++;
        }
    }

    private static void cCheck() {
        for (int i = 0; i < 5; i++) {
            int count = 0;
            for (int j = 0; j < 5; j++) {
                if (map[j][i] == 0) count++;
            }
            if (count == 5) cnt++;
        }
    }

    private static void diagonalCheck() {
        int count = 0;
        for (int i = 0; i < 5; i++) {
            if (map[i][i] == 0) count++;
        }
        if (count == 5) cnt++;

        count = 0;
        for (int i = 0; i < 5; i++) {
            if (map[i][4 - i] == 0) count++;
        }
        if (count == 5) cnt++;
    }
}
