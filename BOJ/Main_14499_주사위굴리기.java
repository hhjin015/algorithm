package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14499_주사위굴리기 {
    static int N, M, K, x, y;
    static int[][] map;
    static int[] command;
    static Dice mainDice;
    // 우 좌 상 하
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};
    static class Dice {
        int top;
        int up;
        int bottom;
        int down;
        int left;
        int right;

        public Dice(int top, int up, int bottom, int down, int left, int right) {
            this.top = top;
            this.up = up;
            this.bottom = bottom;
            this.down = down;
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        command = new int[K];
        map = new int[N][M];
        mainDice = new Dice(0, 0, 0, 0, 0, 0);

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            command[i] = Integer.parseInt(st.nextToken()) - 1;
        }

        for (int t = 0; t < K; t++) {
            int c = command[t];

            int nr = x + dr[c];
            int nc = y + dc[c];

            if (!isBoundary(nr, nc)) continue;

            switch (c) {
                case 0:
                    turnRight();
                    break;
                case 1:
                    turnLeft();
                    break;
                case 2:
                    turnUp();
                    break;
                case 3:
                    turnDown();
                    break;
            }

            if (map[nr][nc] == 0) {
                map[nr][nc] = mainDice.bottom;
            } else {
                mainDice.bottom = map[nr][nc];
                map[nr][nc] = 0;
            }

            x = nr;
            y = nc;

            sb.append(mainDice.top).append("\n");
        }
        System.out.println(sb);
    }

    private static void turnRight() {   // 1
        Dice tempDice = copyDice();
        mainDice.top = tempDice.right;
        mainDice.right = tempDice.bottom;
        mainDice.bottom = tempDice.left;
        mainDice.left = tempDice.top;
    }

    private static void turnLeft() {    // 2
        Dice tempDice = copyDice();
        mainDice.top = tempDice.left;
        mainDice.left = tempDice.bottom;
        mainDice.bottom = tempDice.right;
        mainDice.right = tempDice.top;
    }

    private static void turnUp() {      //3
        Dice tempDice = copyDice();
        mainDice.top = tempDice.down;
        mainDice.down = tempDice.bottom;
        mainDice.bottom = tempDice.up;
        mainDice.up = tempDice.top;
    }

    private static void turnDown() {    //4
        Dice tempDice = copyDice();
        mainDice.top = tempDice.up;
        mainDice.up = tempDice.bottom;
        mainDice.bottom = tempDice.down;
        mainDice.down = tempDice.top;
    }

    private static boolean isBoundary(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < M;
    }

    private static Dice copyDice() {
        return new Dice(mainDice.top, mainDice.up, mainDice.bottom, mainDice.down, mainDice.left, mainDice.right);
    }
}
