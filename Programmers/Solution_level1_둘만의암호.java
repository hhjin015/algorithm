package Programmers;

import java.util.Arrays;

public class Solution_level1_둘만의암호 {
    public static void main(String[] args) {
        System.out.println(Solution("aukks", "wbqd", 5));
    }

    private static String Solution(String s, String skip, int index) {
        boolean[] isSkip = new boolean[26];
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < skip.length(); i++) {
            isSkip[skip.charAt(i) - 97] = true;
        }

        for (int i = 0; i < s.length(); i++) {
            int start = s.charAt(i) - 97;
            int indexCnt = 0;
            if (start == 25) start = -1;

            for (int j = start + 1; j < 26; j++) {
                if (!isSkip[j]) indexCnt++;
                if (indexCnt == index) {
                    sb.append((char) (j + 97));
                    break;
                }
                if (j == 25) j = -1;
            }
        }

        return sb.toString();
    }
}
