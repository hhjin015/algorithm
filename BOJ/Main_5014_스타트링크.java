package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_5014_스타트링크 {
    static int F, S, G, U, D;

    static class Pair {
        int floor;
        int cnt;

        public Pair(int floor, int cnt) {
            this.floor = floor;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken());       // 총 층수
        S = Integer.parseInt(st.nextToken()) - 1;   // 현재 위치
        G = Integer.parseInt(st.nextToken()) - 1;   // 목적지
        U = Integer.parseInt(st.nextToken());       // 위
        D = Integer.parseInt(st.nextToken());       // 아래

        int ans = bfs();
        if (ans == -1) System.out.println("use the stairs");
        else System.out.println(ans);
    }

    private static int bfs() {
        Queue<Pair> q = new LinkedList<>();
        boolean[] visited = new boolean[F];

        visited[S] = true;
        q.add(new Pair(S, 0));

        while (!q.isEmpty()) {
            Pair p = q.poll();
            if (p.floor == G) return p.cnt;

            int nextUp = p.floor + U;
            int nextDown = p.floor - D;

            if (nextUp < F && !visited[nextUp]) {
                visited[nextUp] = true;
                q.add(new Pair(nextUp, p.cnt + 1));
            }
            if (nextDown > -1 && !visited[nextDown]) {
                visited[nextDown] = true;
                q.add(new Pair(nextDown, p.cnt + 1));
            }
        }

        return -1;
    }
}
