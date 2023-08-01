package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_1719_택배_Dijkstra {
    static final int INF = 199001;
    static int N, M;
    static List<List<Node>> list;

    static class Node implements Comparable<Node> {
        int v;
        int dist;

        public Node(int v, int dist) {
            this.v = v;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());

            list.get(a).add(new Node(b, c));
            list.get(b).add(new Node(a, c));
        }

        for (int i = 0; i < N; i++) {
            int[] path = dijkstra(i);

            for (int j = 0; j < N; j++) {
                if (path[j] == 0) sb.append("-").append(" ");
                else sb.append(path[j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static int[] dijkstra(int start_v) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N];
        int[] path = new int[N];
        int[] distance = new int[N];

        Arrays.fill(distance, INF);

        distance[start_v] = 0;
        pq.offer(new Node(start_v, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (visited[node.v]) continue;
            visited[node.v] = true;

            for (int i = 0; i < list.get(node.v).size(); i++) {
                Node to = list.get(node.v).get(i);

                if (distance[to.v] > node.dist + to.dist) {
                    distance[to.v] = node.dist + to.dist;
                    pq.offer(new Node(to.v, distance[to.v]));

                    if (node.v == start_v) path[to.v] = to.v + 1;
                    else path[to.v] = path[node.v];
                }
            }
        }
        return path;
    }
}
