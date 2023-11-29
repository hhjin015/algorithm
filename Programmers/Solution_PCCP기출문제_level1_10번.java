package Programmers;

import java.util.*;

public class Solution_PCCP기출문제_level1_10번 {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(solution(new int[][]{{1, 20300104, 100, 80}, {2, 20300804, 847, 37}, {3, 20300401, 10, 8}},
                "date", 20300501, "remain")));
    }

    private static int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        String[] str = {"code", "date", "maximum", "remain"};
        List<int[]> list = new ArrayList<>();
        int extIdx = 0;
        int sortIdx = 0;

        for (int i = 0; i < 4; i++) {
            if (ext.equals(str[i])) extIdx = i;
            if (sort_by.equals(str[i])) sortIdx = i;
        }

        final int idx = sortIdx;

        for (int i = 0; i < data.length; i++) {
            if (data[i][extIdx] < val_ext) list.add(data[i]);
        }

        list.sort((Comparator.comparingInt(o -> o[idx])));

        int[][] answer = new int[list.size()][4];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}
