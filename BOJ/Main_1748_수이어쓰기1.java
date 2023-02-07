package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1748_수이어쓰기1 {
    static int NToNumber, remain, answer;
    static String str = "";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N = br.readLine();
        NToNumber = Integer.parseInt(N);

        if (NToNumber < 10) {
            System.out.println(NToNumber);
            System.exit(0);
        }

        for (int i = 0; i < N.length() - 1; i++) {
            str += "1";
        }

        remain = NToNumber - (9 * Integer.parseInt(str));
        answer = 0;

        for (int i = 0; i < N.length() - 1; i++) {
            answer += 9 * Math.pow(10, i) * (i + 1);
        }

        answer += remain * N.length();

        System.out.println(answer);

    }
}
