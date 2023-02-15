package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_5567_결혼식 {
    static int N, M;
    static int ans = 0;
    static ArrayList<ArrayList<Integer>> list;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        visit = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            list.get(n1).add(n2);
            list.get(n2).add(n1);
        }

        dfs(1, 0);

        for (boolean b : visit) {
            if (b) ans++;
        }

        System.out.println(ans - 1);
    }

    private static void dfs(int num, int depth) {
        if (depth > 2) return;
        visit[num] = true;

        for (int i = 0; i < list.get(num).size(); i++) {
            int n = list.get(num).get(i);
            dfs(n, depth + 1);
        }
    }
}
