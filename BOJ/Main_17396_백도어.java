package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_17396_백도어 {
    static final long INF = 30000000000L;
    static int N, M;
    static long[] dist;
    static boolean[] visited;
    static List<List<Node>> list;

    static class Node implements Comparable<Node> {
        int v;
        long dist;

        public Node(int v, long dist) {
            this.v = v;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return (int) (this.dist - o.dist);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N];
        dist = new long[N];
        Arrays.fill(dist, INF);

        list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            if (Integer.parseInt(st.nextToken()) == 1) {
                visited[i] = true;
            }
        }
        visited[N - 1] = false;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list.get(from).add(new Node(to, weight));
            list.get(to).add(new Node(from, weight));
        }

        dijkstra();

        if (dist[N - 1] == INF) System.out.println(-1);
        else System.out.println(dist[N - 1]);
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[0] = 0;
        pq.offer(new Node(0, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (visited[node.v] || dist[node.v] > node.dist) continue;
            visited[node.v] = true;

            for (int i = 0; i < list.get(node.v).size(); i++) {
                Node to = list.get(node.v).get(i);

                if (visited[to.v]) continue;
                if (dist[to.v] > node.dist + to.dist) {
                    dist[to.v] = node.dist + to.dist;
                    pq.offer(new Node(to.v, dist[to.v]));
                }
            }
        }
    }
}
