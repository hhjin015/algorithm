package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_13023_ABCDE {
    static List<List<Integer>> relationship = new ArrayList<>();
    static boolean answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            relationship.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            relationship.get(a).add(b);
            relationship.get(b).add(a);
        }

        for (int i = 0; i < N; i++) {
            boolean[] visited = new boolean[N];
            dfs(i, 1, visited);

            if (answer) {
                System.out.println(1);
                System.exit(0);
            }
        }

        System.out.println(0);
    }

    private static void dfs(int node, int depth, boolean[] visited) {
        if (depth == 5) {
            answer = true;
            return;
        }
        visited[node] = true;

        List<Integer> list = relationship.get(node);
        for (int n : list) {
            if (visited[n]) continue;
            dfs(n, depth + 1, visited);
        }

        visited[node] = false;
    }
}
