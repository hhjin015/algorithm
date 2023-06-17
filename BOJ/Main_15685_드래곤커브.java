package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_15685_드래곤커브 {
    static int cnt;
    static boolean[][] map;
    static List<Integer> list;
    static int[] dr = {0, -1, 0, 1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new boolean[101][101];
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());

            makeCurve(d, g);
            drawCurve(r, c);
        }
        checkSquare();

        System.out.println(cnt);
    }

    private static void makeCurve(int d, int g) {
        list = new ArrayList<>();
        list.add(d);

        for (int i = 0; i < g; i++) {
            int size = list.size();
            for (int j = size - 1; j >= 0; j--) {
                int dir = (list.get(j) + 1) % 4;
                list.add(dir);
            }
        }
    }

    private static void drawCurve(int r, int c) {
        map[r][c] = true;

        for (int i = 0; i < list.size(); i++) {
            int dir = list.get(i);

            r += dr[dir];
            c += dc[dir];
            map[r][c] = true;
        }
    }

    private static void checkSquare() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (map[i][j] && map[i][j + 1] && map[i + 1][j] && map[i + 1][j + 1]) cnt++;
            }
        }
    }
}
