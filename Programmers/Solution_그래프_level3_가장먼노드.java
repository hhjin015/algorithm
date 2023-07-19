package Programmers;

import java.util.*;

public class Solution_그래프_level3_가장먼노드 {
    static int[] dist;

    static class Node {
        int v;
        int dist;

        public Node(int v, int dist) {
            this.v = v;
            this.dist = dist;
        }
    }

    public static void main(String[] args) {
        System.out.println(Solution(6, new int[][]{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}}));
    }

    private static int Solution(int n, int[][] edge) {
        List<List<Integer>> list = new ArrayList<>();
        dist = new int[n];

        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < edge.length; i++) {
            list.get(edge[i][0] - 1).add(edge[i][1] - 1);
            list.get(edge[i][1] - 1).add(edge[i][0] - 1);
        }

        int max = getMaxDist(n, list);
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            if (dist[i] == max) ++cnt;
        }

        return cnt;
    }

    private static int getMaxDist(int n, List<List<Integer>> list) {
        Queue<Node> q = new LinkedList<>();
        boolean[] visited = new boolean[n];
        int max = 0;

        visited[0] = true;
        q.offer(new Node(0, 0));

        while (!q.isEmpty()) {
            Node node = q.poll();

            dist[node.v] = node.dist;
            max = Math.max(max, node.dist);

            for (int i = 0; i < list.get(node.v).size(); i++) {
                int to = list.get(node.v).get(i);

                if (!visited[to]) {
                    visited[to] = true;
                    q.offer(new Node(to, node.dist + 1));
                }
            }
        }
        return max;
    }
}
