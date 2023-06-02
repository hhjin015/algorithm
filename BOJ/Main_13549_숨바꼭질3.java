package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_13549_숨바꼭질3 {
    static int N, K;

    static class Pair {
        int num;
        int cnt;

        public Pair(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Pair> q = new LinkedList<>();
        boolean[] visited = new boolean[100001];

        visited[N] = true;
        q.add(new Pair(N, 0));

        while (!q.isEmpty()) {
            Pair p = q.poll();
            if (p.num == K) return p.cnt;

            int nextUp = p.num + 1;
            int nextDown = p.num - 1;
            int nextTeleport = p.num * 2;

            if (nextTeleport < 100001 && !visited[nextTeleport]) {
                visited[nextTeleport] = true;
                q.add(new Pair(nextTeleport, p.cnt));
            }
            if (nextDown >= 0 && !visited[nextDown]) {
                visited[nextDown] = true;
                q.add(new Pair(nextDown, p.cnt + 1));
            }
            if (nextUp < K + 1 && !visited[nextUp]) {
                visited[nextUp] = true;
                q.add(new Pair(nextUp, p.cnt + 1));
            }
        }

        return -1;
    }
}
