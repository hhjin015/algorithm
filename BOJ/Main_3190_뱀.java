package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_3190_뱀 {
    static int N, K, L, time, dir;
    static int[][] map;
    static Map<Integer, String> infos = new LinkedHashMap<>();
    static Deque<Snake> snake = new LinkedList<>();
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    static class Snake {
        int r;
        int c;

        public Snake(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        map = new int[N][N];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;

            map[r][c] = 1;
        }

        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());

            infos.put(Integer.parseInt(st.nextToken()), st.nextToken());
        }

        snake.offer(new Snake(0, 0));
        map[0][0] = -1;

        while (true) {
            ++time;

            Snake head = snake.getFirst();

            int nr = head.r + dr[dir];
            int nc = head.c + dc[dir];

            if (isOutOfRange(nr, nc) || map[nr][nc] == -1) break;

            changeSnake(nr, nc);
            changeDir(infos.get(time));
        }

        System.out.println(time);
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= N;
    }

    private static void changeSnake(int r, int c) {
        if (map[r][c] == 0) {
            Snake tail = snake.removeLast();
            map[tail.r][tail.c] = 0;
        }

        snake.offerFirst(new Snake(r, c));
        map[r][c] = -1;
    }

    private static void changeDir(String value) {
        if (value != null) {
            if (value.equals("D"))
                dir = (dir + 1) % 4;
            else
                dir = (dir + 3) % 4;

            infos.remove(time);
        }
    }
}
