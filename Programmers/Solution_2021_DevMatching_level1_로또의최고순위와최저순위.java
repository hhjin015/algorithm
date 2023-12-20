package Programmers;

import java.util.Arrays;

public class Solution_2021_DevMatching_level1_로또의최고순위와최저순위 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new int[]{44, 1, 0, 0, 31, 25}, new int[]{31, 10, 45, 1, 6, 19})));
        System.out.println(Arrays.toString(solution(new int[]{0, 0, 0, 0, 0, 0}, new int[]{38, 19, 20, 40, 15, 25})));
        System.out.println(Arrays.toString(solution(new int[]{45, 4, 35, 20, 3, 9}, new int[]{20, 9, 3, 45, 4, 35})));
    }

    private static int[] solution(int[] lottos, int[] win_nums) {
        int[] ranks = {6, 5, 4, 3, 2};

        int common = 0;
        int zeroCnt = 0;
        for (int i = 0; i < lottos.length; i++) {
            if (lottos[i] == 0) zeroCnt++;

            for (int j = 0; j < win_nums.length; j++) {
                if (win_nums[j] == lottos[i]) {
                    common++;
                    break;
                }
            }
        }

        int worst = 0;
        int best = 0;
        for (int i = 0; i < ranks.length; i++) {
            if (ranks[i] == common) worst = i + 1;
            if (ranks[i] == common + zeroCnt) best = i + 1;
        }

        if (worst == 0) worst = 6;
        if (best == 0) best = 6;

        return new int[]{best, worst};
    }
}
