package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2839_설탕배달 {
    static int N, ans;
    static boolean flag;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        flag = true;

        dfs(N);
        if (flag) System.out.println(ans);
        else System.out.println(-1);
    }

    private static void dfs(int num) {
        if (num == 1 || num == 2) {
            flag = false;
            return;
        }

        if (num == 0) return;
        if (num % 5 == 0) ans += num / 5;
        else if ((num % 5) % 3 == 0) {
            ans += num / 5;
            ans += (num % 5) / 3;
        } else {
            dfs(num - 3);
            ans += 1;
        }
    }
}
