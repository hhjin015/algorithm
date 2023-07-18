package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_14938_서강그라운드 {
    static int n, m, r;
    static int[] items;
    static boolean[] visited;
    static List<List<Node>> list;

    static class Node {
        int v;
        int dist;

        public Node(int v, int dist) {
            this.v = v;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        items = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int l = Integer.parseInt(st.nextToken());

            list.get(a).add(new Node(b, l));
            list.get(b).add(new Node(a, l));
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            visited = new boolean[n];
            int cnt = 0;

            countItems(new Node(i, 0));

            for (int j = 0; j < n; j++) {
                if (visited[j]) cnt += items[j];
            }

            max = Math.max(max, cnt);
        }

        System.out.println(max);
    }

    private static void countItems(Node node) {
        Queue<Node> q = new LinkedList<>();
        q.offer(node);

        while (!q.isEmpty()) {
            Node n = q.poll();
            visited[n.v] = true;

            for (int i = 0; i < list.get(n.v).size(); i++) {
                if (list.get(n.v).get(i).dist + n.dist <= m) {
                    q.offer(new Node(list.get(n.v).get(i).v, list.get(n.v).get(i).dist + n.dist));
                }
            }
        }
    }
}
