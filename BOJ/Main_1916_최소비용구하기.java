package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1916_최소비용구하기 {
    private static final int INF = Integer.MAX_VALUE;
    static List<ArrayList<Node>> list;
    static int[] distance;
    static boolean[] visited;
    static int N, M;

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
            return this.weight - o.weight;    //weight 기준 오름차순
        }

        @Override
        public String toString() {
            return "Node [to=" + to + ", weight=" + weight + "]";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());    //도시 개수
        M = Integer.parseInt(br.readLine());    //버스 개수

        list = new ArrayList<>();
        distance = new int[N + 1];    //0은 dummy
        Arrays.fill(distance, INF);

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            list.get(s).add(new Node(e, w));
        }

        st = new StringTokenizer(br.readLine());
        int departure = Integer.parseInt(st.nextToken());    //출발
        int destination = Integer.parseInt(st.nextToken());    //도착

        System.out.println(dijkstra(departure, destination));
    }

    private static int dijkstra(int departure, int destination) {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        visited = new boolean[N + 1];

        distance[departure] = 0;
        pq.offer(new Node(departure, 0));

        while (!pq.isEmpty()) {
            Node qNode = pq.poll();
            int startNum = qNode.to;

            if (!visited[startNum]) {        //방문하지 않았다면
                visited[startNum] = true;    //방문체크

                for (Node node : list.get(startNum)) {
                    if (!visited[node.to] && distance[node.to] > distance[startNum] + node.weight) {    //방문하지 않았고 직접비용보다 경유비용이 작다면
                        distance[node.to] = distance[startNum] + node.weight;    //경유비용으로 값 세팅
                        pq.offer(new Node(node.to, distance[node.to]));
                    }
                }
            }
        }
        return distance[destination];
    }
}
