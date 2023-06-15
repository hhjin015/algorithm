package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_4485_녹색옷입은애가젤다지 {
    static int N;
    static int INF = Integer.MAX_VALUE;
    static int[][] map, rupees;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int size;

        public Node(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }

        @Override
        public int compareTo(Node o) {
            return this.size - o.size;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int round = 0;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) break;

            round++;
            map = new int[N][N];
            rupees = new int[N][N];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N; i++) {
                Arrays.fill(rupees[i], INF);
            }

            bfs();

            sb.append("Problem ").append(round).append(": ").append(rupees[N - 1][N - 1]).append("\n");
        }
        System.out.println(sb);
    }

    private static void bfs() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][N];

        pq.offer(new Node(0, 0, map[0][0]));
        visited[0][0] = true;

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            for (int d = 0; d < 4; d++) {
                int nr = node.x + dr[d];
                int nc = node.y + dc[d];

                if (isOutOfRange(nr, nc) || visited[nr][nc]) continue;

                if (rupees[nr][nc] > node.size + map[nr][nc]) {
                    rupees[nr][nc] = node.size + map[nr][nc];
                    visited[nr][nc] = true;
                    pq.offer(new Node(nr, nc, node.size + map[nr][nc]));
                }
            }
        }
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= N;
    }
}
