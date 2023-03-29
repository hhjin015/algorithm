package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14888_연산자끼워넣기 {
    static int N, min, max;
    static int[] operation, numbers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        numbers = new int[N];
        operation = new int[4];
        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operation[i] = Integer.parseInt(st.nextToken());
        }

        dfs(numbers[0], 1);

        System.out.println(max);
        System.out.println(min);
    }

    private static void dfs(int num, int idx) {
        if (idx == N) {
            max = Math.max(max, num);
            min = Math.min(min, num);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operation[i] > 0) {
                operation[i]--;

                switch (i) {
                    case 0:
                        dfs(num + numbers[idx], idx + 1);
                        break;
                    case 1:
                        dfs(num - numbers[idx], idx + 1);
                        break;
                    case 2:
                        dfs(num * numbers[idx], idx + 1);
                        break;
                    case 3:
                        dfs(num / numbers[idx], idx + 1);
                        break;
                }
                operation[i]++;
            }
        }
    }
}
