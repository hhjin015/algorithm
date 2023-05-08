package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution_완전탐색_level1_모의고사 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(Solution(new int[]{1, 2, 3, 4, 5})));
        System.out.println(Arrays.toString(Solution(new int[]{1, 3, 2, 4, 2})));
    }

    private static int[] Solution(int[] answers) {
        int[] person1 = {1, 2, 3, 4, 5};
        int[] person2 = {2, 1, 2, 3, 2, 4, 2, 5};
        int[] person3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};

        int[] count = new int[3];

        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == person1[i % 5]) count[0]++;
            if (answers[i] == person2[i % 8]) count[1]++;
            if (answers[i] == person3[i % 10]) count[2]++;
        }

        int max = Math.max(count[0], Math.max(count[1], count[2]));

        List<Integer> list = new ArrayList<>();
        if (max == count[0]) list.add(1);
        if (max == count[1]) list.add(2);
        if (max == count[2]) list.add(3);

        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}
