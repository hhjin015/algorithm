package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1389_케빈베이컨의6단계법칙 {
    static int N, M;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken()) - 1;
            int n2 = Integer.parseInt(st.nextToken()) - 1;

            graph[n1].add(n2);
            graph[n2].add(n1);
        }

        int minDis = Integer.MAX_VALUE;
        int minIdx = -1;
        for (int i = 0; i < N; i++) {
            int dis = bfs(i);

            if (dis < minDis) {
                minDis = dis;
                minIdx = i;
            }
        }
        
        System.out.println(minIdx + 1);
    }

    private static int bfs(int start) {
        boolean[] visited = new boolean[N];
        Queue<int[]> q = new LinkedList<>();
        int cnt = 0;

        visited[start] = true;
        q.offer(new int[]{start, 0});

        while (!q.isEmpty()) {
            int[] p = q.poll();
            cnt += p[1];

            for (int i = 0; i < graph[p[0]].size(); i++) {
                int n = graph[p[0]].get(i);

                if (visited[n]) continue;

                visited[n] = true;
                q.offer(new int[]{n, p[1] + 1});
            }
        }
        return cnt;
    }
}
