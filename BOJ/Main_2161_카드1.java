package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main_2161_카드1 {
    private static int N;
    private static StringBuilder sb;
    private static Queue<Integer> q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        q = new LinkedList<>();
        sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            q.add(i);
        }

        while (q.size() != 1) {
            sb.append(q.poll() + " ");

            q.add(q.poll());
        }

        sb.append(q.poll());
        System.out.println(sb);
    }
}
