package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_1475_방번호 {
    static int num, cnt;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        arr = new int[10];
        for (int i = 0; i < str.length(); i++) {
            num = str.charAt(i) - '0';

            if (num == 6) {
                if (arr[6] > arr[9]) arr[9]++;
                else arr[6]++;
            } else if (num == 9) {
                if (arr[9] > arr[6]) arr[6]++;
                else arr[9]++;
            } else arr[num]++;
        }

        cnt = Integer.MIN_VALUE;

        for (int i = 0; i < 10; i++) {
            cnt = Math.max(cnt, arr[i]);
        }
        System.out.println(cnt);
    }
}

