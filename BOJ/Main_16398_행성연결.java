package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_16398_행성연결 {
    static int N;
    static int[][] arr;
    static int[] parents;
    static List<Edge> list;

    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        parents = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        list = new ArrayList<>();
        int idx = 1;
        for (int i = 0; i < N - 1; i++) {
            for (int j = idx; j < N; j++) {
                list.add(new Edge(i, j, arr[i][j]));
            }
            ++idx;
        }

        Collections.sort(list);
        makeSet();

        long ans = 0;
        int cnt = 0;
        for (int i = 0; i < list.size(); i++) {
            Edge e = list.get(i);
            if (find(e.from) == find(e.to)) continue;
            union(e.from, e.to);
            ans += e.weight;
            ++cnt;

            if (cnt == N - 1) break;
        }

        System.out.println(ans);
    }

    private static void makeSet() {
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }
    }

    private static int find(int x) {
        if (parents[x] == x) return x;
        else return find(parents[x]);
    }

    private static void union(int a, int b) {
        int p1 = find(a);
        int p2 = find(b);

        parents[p2] = p1;
    }
}
