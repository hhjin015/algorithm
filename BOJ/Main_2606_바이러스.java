package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main_2606_바이러스 {
    private static boolean[] visit;
    private static int edge;
    private static int ans = 0;
    private static ArrayList<Integer>[] adList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int vertex = Integer.parseInt(br.readLine());
        edge = Integer.parseInt(br.readLine());
        visit = new boolean[vertex + 1];

        adList = new ArrayList<>[vertex + 1];
        for (int i = 1; i <= vertex; i++) {
            adList[i] = new ArrayList<>();
        }

        for (int i = 0; i < edge; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            adList[start].add(end);
            adList[end].add(start);
        }

        System.out.println(dfs(1));
    }

    private static int dfs(int start) {
        visit[start] = true;

        for (int n : adList[start]) {
            if (!visit[n]) {
                ans++;
                dfs(n);
            }
        }
        return ans;
    }
}
