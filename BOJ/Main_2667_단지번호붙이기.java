package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2667_단지번호붙이기 {
    static int N;
    static int apartNum = 0;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {
                    apartNum++;
                    list.add(bfs(i, j));
                }
            }
        }
        sb.append(apartNum).append("\n");
        Collections.sort(list);

        for (int n : list) {
            sb.append(n).append("\n");
        }
        System.out.println(sb.deleteCharAt(sb.length() - 1));
    }

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int bfs(int x, int y) {
        Queue<Pair> q = new LinkedList<>();
        q.offer(new Pair(x, y));
        map[x][y] = 0;
        int cnt = 0;

        while (!q.isEmpty()) {
            Pair p = q.poll();
            cnt++;

            for (int d = 0; d < 4; d++) {
                int nr = p.x + dr[d];
                int nc = p.y + dc[d];

                if (isBoundary(nr, nc) && map[nr][nc] == 1) {
                    Pair np = new Pair(nr, nc);
                    q.add(np);
                    map[nr][nc] = 0;
                }
            }
        }
        return cnt;
    }

    private static boolean isBoundary(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
