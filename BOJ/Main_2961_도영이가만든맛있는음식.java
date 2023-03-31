package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2961_도영이가만든맛있는음식 {
    static int N, min;
    static int[][] ingredient;
    static boolean[] isSelected;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        ingredient = new int[N][2];
        isSelected = new boolean[N];
        min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            ingredient[i][0] = Integer.parseInt(st.nextToken());    // 신맛
            ingredient[i][1] = Integer.parseInt(st.nextToken());    // 쓴맛
        }

        subset(0);
        System.out.println(min);
    }

    private static void subset(int cnt) {
        if (cnt == N) {
            int sour = 1;
            int bitter = 0;
            int count = 0;

            for (int i = 0; i < N; i++) {
                if (isSelected[i]) {
                    sour *= ingredient[i][0];
                    bitter += ingredient[i][1];
                    count++;
                }
            }
            if (count == 0) return;
            else min = Math.min(min, Math.abs(sour - bitter));
            return;
        }
        isSelected[cnt] = true;
        subset(cnt + 1);

        isSelected[cnt] = false;
        subset(cnt + 1);
    }
}
