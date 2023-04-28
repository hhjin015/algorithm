package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1647_도시분할계획 {
    static int V, E;
    static int[] parents;
    static Edge[] edges;

    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        edges = new Edge[E];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(from, to, weight);
        }

        Arrays.sort(edges);
        makeSet();

        int max = Integer.MIN_VALUE;
        int result = 0;
        int cnt = 0;
        for (Edge e : edges) {
            if (union(e.from, e.to)) {
                result += e.weight;
                max = Math.max(max, e.weight);
                if (++cnt == V - 1) break;
            }
        }

        System.out.println(result - max);
    }

    private static void makeSet() {
        parents = new int[V + 1];

        for (int i = 1; i <= V; i++) {
            parents[i] = i;
        }
    }

    private static int find(int a) {
        if (a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return false;

        parents[bRoot] = aRoot;
        return true;
    }
}
