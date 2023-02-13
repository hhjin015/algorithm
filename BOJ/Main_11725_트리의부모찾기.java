package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_11725_트리의부모찾기 {
    static int N;
    static ArrayList<ArrayList<Integer>> tree;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        tree = new ArrayList<>();
        parent = new int[N + 1];
        parent[1] = 1;

        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            tree.get(n1).add(n2);
            tree.get(n2).add(n1);
        }

        bfs();

        for (int i = 2; i < parent.length; i++) {
            sb.append(parent[i]).append("\n");
        }
        System.out.println(sb);
    }

    private static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(1);

        while (!q.isEmpty()) {
            int num = q.poll();

            for (int i = 0; i < tree.get(num).size(); i++) {
                int n = tree.get(num).get(i);
                if (parent[n] == 0) {
                    parent[n] = num;
                    q.add(n);
                }
            }
        }
    }
}
