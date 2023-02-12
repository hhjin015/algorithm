package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1753_최단경로 {
    static final int INF = Integer.MAX_VALUE;
    static List<ArrayList<Node>> list;
    static int[] dist;
    static boolean[] visited;
    static int V, E;

    static class Node implements Comparable<Node> {
        int to;
        int weight;

        public Node(int to, int weight) {
            super();
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;    // 오름차순 정렬
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        int startV = Integer.parseInt(br.readLine());    // 시작정점

        list = new ArrayList<>();
        visited = new boolean[V + 1];
        dist = new int[V + 1];

        Arrays.fill(dist, INF);

        for (int i = 0; i <= V; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list.get(u).add(new Node(v, w));
        }

        dijkstra(startV);

        for (int i = 1; i <= V; i++) {
            System.out.println(dist[i] == INF ? "INF" : dist[i]);
        }
    }

    private static void dijkstra(int startV) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[startV] = 0;   // 출발지에서 자기 자신으로 오는 직접비용 갱신
        pq.offer(new Node(startV, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int num = node.to;

            if (!visited[num]) {     // 방문하지 않았다면
                visited[num] = true; // 방문체크

                for (Node n : list.get(num)) {
                    if (!visited[n.to] && dist[n.to] > dist[num] + n.weight) {    //방문하지 않았고 경유비용이 직접비용보다 작다면
                        dist[n.to] = dist[num] + n.weight;    // 경유비용으로 값 세팅

                        pq.offer(new Node(n.to, dist[n.to]));
                    }
                }
            }
        }
    }
}

