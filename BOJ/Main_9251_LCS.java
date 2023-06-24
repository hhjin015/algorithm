package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9251_LCS {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] stringA = br.readLine().toCharArray();
        char[] stringB = br.readLine().toCharArray();
        int[][] LCS = new int[stringA.length + 1][stringB.length + 1];

        for (int i = 1; i <= stringA.length; i++) {
            for (int j = 1; j <= stringB.length; j++) {
                if (stringA[i - 1] == stringB[j - 1]) {
                    LCS[i][j] = LCS[i - 1][j - 1] + 1;
                } else {
                    LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);
                }
            }
        }

        System.out.println(LCS[stringA.length][stringB.length]);
    }
}
