package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_11724_연결요소의개수 {
    static int N, M, ans;
    static ArrayList<ArrayList<Integer>> list;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visit = new boolean[N];
        list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken()) - 1;
            int n2 = Integer.parseInt(st.nextToken()) - 1;

            list.get(n1).add(n2);
            list.get(n2).add(n1);
        }

        for (int i = 0; i < N; i++) {
            if (!visit[i]) {
                dfs(i);
                ans++;
            }
        }
        System.out.println(ans);
    }

    private static void dfs(int n) {
        if (visit[n]) return;
        else {
            visit[n] = true;
            for (int i = 0; i < list.get(n).size(); i++) {
                int num = list.get(n).get(i);
                dfs(num);
            }
        }
    }
}
