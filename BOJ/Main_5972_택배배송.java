package BOJ;

import java.io.*;
import java.util.*;

public class Main_5972_택배배송 {
    static int N, M;
    static final int INF = Integer.MAX_VALUE;
    static int[] dist;
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

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dist = new int[N];
        list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            list.get(a).add(new Node(b, s));
            list.get(b).add(new Node(a, s));
        }

        Arrays.fill(dist, INF);

        dijkstra();

        System.out.println(dist[N - 1]);
    }

    private static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N];

        dist[0] = 0;
        pq.offer(new Node(0, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (visited[node.v]) continue;
            visited[node.v] = true;

            for (int i = 0; i < list.get(node.v).size(); i++) {
                Node to = list.get(node.v).get(i);

                if (node.dist + to.dist < dist[to.v]) {
                    dist[to.v] = node.dist + to.dist;
                    pq.offer(new Node(to.v, dist[to.v]));
                }
            }
        }
    }
}
