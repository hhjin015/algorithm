package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_1068_트리 {
    static int N, root, cnt;
    static List<ArrayList<Integer>> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(new ArrayList<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            if (n == -1) root = i;
            else {
                list.get(n).add(i);
            }
        }

        int remove = Integer.parseInt(br.readLine());
        if (remove == root) {
            System.out.println(0);
            System.exit(0);
        }

        removeNode(remove);
        recur(root);

        System.out.println(cnt);
    }

    private static void removeNode(int remove) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < list.get(i).size(); j++) {
                if (list.get(i).get(j) == remove) {
                    list.get(i).remove(j);
                    return;
                }
            }
        }
    }

    private static void recur(int node) {
        if (list.get(node).size() == 0) {
            cnt++;
            return;
        }

        for (int i : list.get(node)) {
            recur(i);
        }
    }
}
