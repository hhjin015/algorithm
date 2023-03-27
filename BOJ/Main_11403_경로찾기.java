package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_11403_경로찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // i -> k, k -> j
        for (int k = 0; k < N; k++) {            // 경유지
            for (int i = 0; i < N; i++) {        // 출발지
                for (int j = 0; j < N; j++) {    // 도착지
                    if (arr[i][k] == 1 && arr[k][j] == 1) {
                        arr[i][j] = 1;
                    }
                }
            }
        }

        for (int[] a : arr) {
            for (int aa : a) {
                sb.append(aa + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
