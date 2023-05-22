package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_12851_숨바꼭질2 {
    static int N, K, cnt, minTime;

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

        bfs();
        System.out.println(minTime);
        System.out.println(cnt);
    }

    private static void bfs() {
        Queue<Pair> q = new LinkedList<>();
        int[] visited = new int[100001];

        visited[N]++;
        q.add(new Pair(N, 0));

        while (!q.isEmpty()) {
            Pair p = q.poll();

            if (p.num == K) {
                if (cnt == 0) minTime = p.cnt;
                if (minTime == p.cnt) cnt++;
                continue;
            }

            for (int i = 0; i < 3; i++) {
                int next;
                if (i == 0) next = p.num + 1;
                else if (i == 1) next = p.num - 1;
                else next = p.num * 2;

                if (next < 0 || next > 100000) continue;

                if (visited[next] == 0 || visited[next] == p.cnt + 1) {
                    visited[next] = p.cnt + 1;
                    q.add(new Pair(next, p.cnt + 1));
                }
            }
        }
    }
}


