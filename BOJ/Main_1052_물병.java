package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_1052_물병 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int buy = 0;
        while (true) {
            int cnt = 0;
            int copy = N;

            while (copy != 0) {
                if (copy % 2 == 1) {
                    cnt++;
                }
                copy /= 2;
            }

            if (cnt <= K) break;

            N += 1;
            buy += 1;
        }

        System.out.println(buy);
    }
}
