package Programmers;

import java.util.HashMap;
import java.util.Map;

public class Solution_2022_KAKAO_TECH_INTERNSHIP_level1_성격유형검사하기 {
    public static void main(String[] args) {
        System.out.println(solution(new String[]{"AN", "CF", "MJ", "RT", "NA"}, new int[]{5, 3, 2, 7, 5}));
        System.out.println(solution(new String[]{"TR", "RT", "TR"}, new int[]{7, 1, 3}));
    }

    private static String solution(String[] survey, int[] choices) {
        char[][] types = {{'R', 'T'}, {'C', 'F'}, {'J', 'M'}, {'A', 'N'}};
        int[] scores = {3, 2, 1, 0, 1, 2, 3};
        Map<Character, Integer> hm = new HashMap<>();

        for (char[] c : types) {
            hm.put(c[0], 0);
            hm.put(c[1], 0);
        }

        for (int i = 0; i < survey.length; i++) {
            String str = survey[i];
            char first = str.charAt(0);
            char second = str.charAt(1);

            if (choices[i] < 4) hm.put(first, hm.get(first) + scores[choices[i] - 1]);
            else if (choices[i] > 4) hm.put(second, hm.get(second) + scores[choices[i] - 1]);
        }

        StringBuilder sb = new StringBuilder();
        for (char[] c : types) {
            sb.append(getChar(c[0], c[1], hm));
        }

        return sb.toString();
    }

    private static char getChar(char a, char b, Map<Character, Integer> map) {
        if (map.get(a) >= map.get(b)) return a;
        else return b;
    }
}
