package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_2665_미로만들기 {
    static int N;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static class Spot implements Comparable<Spot> {
        int x;
        int y;
        int breakCnt;

        public Spot(int x, int y, int breakCnt) {
            this.x = x;
            this.y = y;
            this.breakCnt = breakCnt;
        }

        @Override
        public int compareTo(Spot o) {
            return this.breakCnt - o.breakCnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        PriorityQueue<Spot> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][N];

        pq.offer(new Spot(0, 0, 0));
        visited[0][0] = true;

        while (!pq.isEmpty()) {
            Spot spot = pq.poll();

            for (int d = 0; d < 4; d++) {
                int nr = spot.x + dr[d];
                int nc = spot.y + dc[d];

                if (nr == N - 1 && nc == N - 1) return spot.breakCnt;

                if (isOutOfRange(nr, nc) || visited[nr][nc]) continue;

                visited[nr][nc] = true;

                if (map[nr][nc] == 0) {
                    pq.offer(new Spot(nr, nc, spot.breakCnt + 1));
                } else {
                    pq.offer(new Spot(nr, nc, spot.breakCnt));
                }
            }
        }
        return -1;
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= N;
    }
}
