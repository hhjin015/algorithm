package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * PriorityQueue + visited[] = 928ms
 * PriorityQueue = 1048ms
 * Queue = 992ms
 * Queue + visited[] = FAIL (먼저 간 경로가 최단 경로라는 보장X)
 */
public class Main_10282_해킹 {
    static boolean[] visited;
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
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int computer = Integer.parseInt(st.nextToken());
            int dependency = Integer.parseInt(st.nextToken());
            int hackedCom = Integer.parseInt(st.nextToken()) - 1;

            visited = new boolean[computer];
            dist = new int[computer];
            list = new ArrayList<>();

            for (int i = 0; i < computer; i++) {
                list.add(new ArrayList<>());
            }

            for (int i = 0; i < dependency; i++) {
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken()) - 1;
                int b = Integer.parseInt(st.nextToken()) - 1;
                int s = Integer.parseInt(st.nextToken());

                list.get(b).add(new Node(a, s));
            }

            Arrays.fill(dist, Integer.MAX_VALUE);

            infect(hackedCom);

            int cnt = 0;
            int max = 0;
            for (int i = 0; i < computer; i++) {
                if (visited[i]) {
                    ++cnt;
                    max = Math.max(max, dist[i]);
                }
            }

            sb.append(cnt).append(" ").append(max).append("\n");
        }

        System.out.println(sb);
    }

    private static void infect(int startV) {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(startV, 0));
        dist[startV] = 0;

        while (!q.isEmpty()) {
            Node node = q.poll();

            if (visited[node.v]) continue;

            visited[node.v] = true;

            for (int i = 0; i < list.get(node.v).size(); i++) {
                Node to = list.get(node.v).get(i);
                if (node.dist + to.dist < dist[to.v]) {
                    dist[to.v] = node.dist + to.dist;
                    q.offer(new Node(to.v, node.dist + to.dist));
                }
            }
        }
    }
}
