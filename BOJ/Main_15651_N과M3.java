package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15651_N과M3 {
    static int N, R;
    static int[] inputs, pick;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        inputs = new int[N];
        pick = new int[R];

        for (int i = 0; i < N; i++) {
            inputs[i] = i + 1;
        }

        permutation(0);
        System.out.println(sb);
    }

    private static void permutation(int cnt) {
        if (cnt == R) {
            for (int i = 0; i < R; i++) {
                sb.append(pick[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            pick[cnt] = inputs[i];

            permutation(cnt + 1);
        }
    }
}

