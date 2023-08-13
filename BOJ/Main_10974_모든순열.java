package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_10974_모든순열 {
    static int N;
    static int[] inputs, numbers;
    static boolean[] isSelected;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        inputs = new int[N];
        numbers = new int[N];
        isSelected = new boolean[N];

        for (int i = 0; i < N; i++) {
            inputs[i] = i + 1;
        }

        permutation(0);

        System.out.println(sb);
    }

    private static void permutation(int cnt) {
        if (cnt == N) {
            for (int a : numbers) {
                sb.append(a).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if (isSelected[i]) continue;

            numbers[cnt] = inputs[i];
            isSelected[i] = true;

            permutation(cnt + 1);
            isSelected[i] = false;
        }
    }
}
