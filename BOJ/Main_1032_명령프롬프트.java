package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_1032_명령프롬프트 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        String str = br.readLine();
        int length = str.length();

        if (n == 1) {
            System.out.println(str);
            System.exit(0);
        }

        char[] result = str.toCharArray();
        char[] cur = new char[length];

        for (int t = 0; t < n - 1; t++) {
            str = br.readLine();
            for (int i = 0; i < length; i++) {
                cur[i] = str.charAt(i);

                if (cur[i] != result[i]) result[i] = '?';

            }
        }
        for(char c : result) {
            System.out.print(c);
        }
    }
}
