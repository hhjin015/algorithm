package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class Solution_SummerWinterCoding_level2_배달 {
    static List<List<Node>> list;
    static int[] dist;
    static final int INF = 500000;

    static class Node implements Comparable<Node> {
        int v;
        int weight;

        public Node(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) {
        System.out.println(Solution(5, new int[][]{{1, 2, 1}, {2, 3, 3}, {5, 2, 2}, {1, 4, 2}, {5, 3, 1}, {5, 4, 2}}, 3));
        System.out.println(Solution(6, new int[][]{{1, 2, 1}, {1, 3, 2}, {2, 3, 2}, {3, 4, 3}, {3, 5, 2}, {3, 5, 3}, {5, 6, 1}}, 4));
    }

    private static int Solution(int N, int[][] road, int K) {
        list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < road.length; i++) {
            int a = road[i][0] - 1;
            int b = road[i][1] - 1;
            int w = road[i][2];

            list.get(a).add(new Node(b, w));
            list.get(b).add(new Node(a, w));
        }

        dist = new int[N];
        Arrays.fill(dist, INF);

        dijkstra(0, N);

        int ans = 0;
        for (int a : dist) {
            if (a <= K) ++ans;
        }

        return ans;
    }

    private static void dijkstra(int start, int N) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N];

        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            visited[node.v] = true;
            for (int i = 0; i < list.get(node.v).size(); i++) {
                Node to = list.get(node.v).get(i);

                if (visited[to.v]) continue;
                if (dist[to.v] > dist[node.v] + to.weight) {
                    dist[to.v] = dist[node.v] + to.weight;

                    pq.offer(new Node(to.v, dist[to.v]));
                }
            }
        }
    }
}
