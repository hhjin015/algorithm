package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_11728_배열합치기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] result = new int[N + M];
        int[] arrayA = new int[N];
        int[] arrayB = new int[M];

        int idx = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arrayA[i] = Integer.parseInt(st.nextToken());
            result[idx++] = arrayA[i];
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            arrayB[i] = Integer.parseInt(st.nextToken());
            result[idx++] = arrayB[i];
        }

        Arrays.sort(result);

        StringBuilder sb = new StringBuilder();
        for (int a : result) {
            sb.append(a).append(" ");
        }

        System.out.println(sb);
    }
}
