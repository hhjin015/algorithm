package Programmers;

import java.util.*;

public class Solution_level1_대충만든자판 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[]{"ABACD", "BCEFD"}, new String[]{"ABCD", "AABB"})));
        System.out.println(Arrays.toString(solution(new String[]{"AA"}, new String[]{"B"})));
        System.out.println(Arrays.toString(solution(new String[]{"BC"}, new String[]{"AC", "BC"})));
        System.out.println(Arrays.toString(solution(new String[]{"AGZ", "BSSS"}, new String[]{"ASA", "BGZ"})));
    }

    private static int[] solution(String[] keymap, String[] targets) {
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < keymap.length; i++) {
            for (int j = 0; j < keymap[i].length(); j++) {
                char c = keymap[i].charAt(j);

                if (map.get(c) == null) map.put(c, j + 1);
                else map.put(c, Math.min(map.get(c), j + 1));
            }
        }

        int[] answer = new int[targets.length];
        for (int i = 0; i < targets.length; i++) {
            int sum = 0;
            for (int j = 0; j < targets[i].length(); j++) {
                if (map.get(targets[i].charAt(j)) == null) {
                    sum = -1;
                    break;
                }
                sum += map.get(targets[i].charAt(j));
            }
            answer[i] = sum;
        }

        return answer;
    }
}
