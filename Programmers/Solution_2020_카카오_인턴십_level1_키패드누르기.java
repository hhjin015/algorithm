package Programmers;

public class Solution_2020_카카오_인턴십_level1_키패드누르기 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 3, 4, 5, 8, 2, 1, 4, 5, 9, 5}, "right"));
        System.out.println(solution(new int[]{7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2}, "left"));
        System.out.println(solution(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0}, "right"));
    }

    private static String solution(int[] numbers, String hand) {
        int[][] keypad = {{3, 1}, {0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 1}, {1, 2}, {2, 0}, {2, 1}, {2, 2}};
        int[] lCur = new int[]{3, 0};
        int[] rCur = new int[]{3, 2};
        StringBuilder sb = new StringBuilder();

        for (int i : numbers) {
            if (i == 1 || i == 4 || i == 7) common(lCur, keypad[i], sb, "L");
            else if (i == 3 || i == 6 || i == 9) common(rCur, keypad[i], sb, "R");
            else {
                int lDiff = getDiff(lCur, keypad[i]);
                int rDiff = getDiff(rCur, keypad[i]);

                if (lDiff < rDiff) common(lCur, keypad[i], sb, "L");
                else if (rDiff < lDiff) common(rCur, keypad[i], sb, "R");
                else {
                    if (hand.equals("right")) common(rCur, keypad[i], sb, "R");
                    else common(lCur, keypad[i], sb, "L");
                }
            }
        }

        return sb.toString();
    }

    private static int getDiff(int[] cur, int[] keypad) {
        return Math.abs(cur[0] - keypad[0]) + Math.abs(cur[1] - keypad[1]);
    }

    private static void common(int[] arr1, int[] arr2, StringBuilder sb, String str) {
        arr1[0] = arr2[0];
        arr1[1] = arr2[1];

        sb.append(str);
    }
}