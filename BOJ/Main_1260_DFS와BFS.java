package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1260_DFS와BFS {
    static int N, M, start_v;
    static boolean[] visit;
    static ArrayList<ArrayList<Integer>> list;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        start_v = Integer.parseInt(st.nextToken());

        visit = new boolean[N + 1];
        list = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            list.get(n1).add(n2);
            list.get(n2).add(n1);
        }
        for (ArrayList<Integer> a : list) {
            Collections.sort(a);
        }

        dfs(start_v);

        Arrays.fill(visit, false);
        sb.append("\n");

        bfs(start_v);
        System.out.println(sb);
    }

    private static void dfs(int start) {
        visit[start] = true;
        sb.append(start + " ");
        for (int i = 0; i < list.get(start).size(); i++) {
            int n = list.get(start).get(i);

            if (!visit[n]) {
                dfs(n);
            }
        }
    }

    private static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visit[start] = true;
        sb.append(start + " ");

        while (!q.isEmpty()) {
            int p = q.poll();

            for (int i = 0; i < list.get(p).size(); i++) {
                int n = list.get(p).get(i);

                if (!visit[n]) {
                    visit[n] = true;
                    q.add(n);
                    sb.append(n + " ");
                }
            }
        }
    }
}
