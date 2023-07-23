package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_11779_최소비용구하기2 {
    static int[] dist, path;
    static List<List<Node>> cityList;

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
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        dist = new int[n];
        path = new int[n];
        cityList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            cityList.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            cityList.get(a).add(new Node(b, s));
        }

        st = new StringTokenizer(br.readLine());
        int start_v = Integer.parseInt(st.nextToken()) - 1;
        int end_v = Integer.parseInt(st.nextToken()) - 1;

        Arrays.fill(dist, Integer.MAX_VALUE);

        dijkstra(start_v, n);

        // 최소비용
        System.out.println(dist[end_v]);

        // 도시의 개수
        List<Integer> pathList = new ArrayList<>();
        int cnt = 1;
        int now = end_v;
        pathList.add(now + 1);

        while (now != start_v) {
            pathList.add(path[now] + 1);
            now = path[now];
            ++cnt;
        }

        System.out.println(cnt);

        // 경로
        for (int i = pathList.size() - 1; i >= 0; i--) {
            System.out.print(pathList.get(i) + " ");
        }
    }

    private static void dijkstra(int start, int N) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N];
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (visited[node.v]) continue;

            visited[node.v] = true;

            for (int i = 0; i < cityList.get(node.v).size(); i++) {
                Node to = cityList.get(node.v).get(i);

                if (node.dist + to.dist < dist[to.v]) {
                    path[to.v] = node.v;
                    dist[to.v] = node.dist + to.dist;

                    pq.offer(new Node(to.v, dist[to.v]));
                }
            }
        }
    }
}
