package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_1110_더하기사이클 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int quotient = N / 10;  // 몫
        int leaves = N % 10;    // 나머지
        boolean flag = true;
        int ans = 0;

        while (flag) {
            int sum = quotient + leaves;
            int newNum = (leaves * 10) + (sum % 10);

            if(newNum == N) flag = false;

            quotient = newNum / 10;
            leaves = newNum % 10;
            ans ++;
        }

        System.out.println(ans);

    }
}
