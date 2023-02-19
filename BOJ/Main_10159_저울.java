package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_10159_저울 {
    static int N, M;
    static boolean[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        arr = new boolean[N][N];

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            arr[a][b] = true;    // 직접경로
        }

        // floyd
        for (int k = 0; k < N; k++) {            // 경유지
            for (int i = 0; i < N; i++) {        // 출발지
                for (int j = 0; j < N; j++) {    // 도착지
                    if (arr[i][k] && arr[k][j]) {
                        arr[i][j] = true;
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            int ans = N - 1;
            for (int j = 0; j < N; j++) {
                if (arr[i][j] || arr[j][i]) ans--;
            }
            sb.append(ans).append('\n');
        }
        System.out.println(sb);
    }
}
