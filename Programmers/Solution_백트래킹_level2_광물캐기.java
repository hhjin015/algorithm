package Programmers;

public class Solution_백트래킹_level2_광물캐기 {
    static int[][] cost = new int[][]{{1, 1, 1}, {5, 1, 1}, {25, 5, 1}};
    static int min;

    public static void main(String[] args) {
        int[] picks = new int[]{1, 3, 2};
        String[] minerals = new String[]{"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"};
        System.out.println(Solution(picks, minerals));

        picks = new int[]{0, 1, 1};
        minerals = new String[]{"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron", "diamond"};
        System.out.println(Solution(picks, minerals));
    }

    private static int Solution(int[] picks, String[] minerals) {
        int size = 0;
        for (int i : picks) {
            size += i;
        }

        min = Integer.MAX_VALUE;
        int[] numbers = new int[size];
        int[] mineralList = new int[minerals.length];

        for (int i = 0; i < minerals.length; i++) {
            if (minerals[i].equals("diamond")) mineralList[i] = 0;
            else if (minerals[i].equals("iron")) mineralList[i] = 1;
            else mineralList[i] = 2;
        }

        combination(0, numbers, mineralList, picks);

        return min;
    }

    private static void combination(int cnt, int[] numbers, int[] mineralList, int[] picks) {
        if (cnt == numbers.length) {
            min = Math.min(min, getMin(numbers, mineralList));
            return;
        }

        for (int i = 0; i < picks.length; i++) {
            if (picks[i] > 0) {
                picks[i]--;
                numbers[cnt] = i;
                combination(cnt + 1, numbers, mineralList, picks);
                picks[i]++;
            }
        }
    }

    private static int getMin(int[] numbers, int[] mineralList) {
        int total = 0;
        int mIdx = 0;

        for (int i : numbers) {
            int cnt = 5;
            while (cnt-- > 0) {
                if (mIdx == mineralList.length) return total;
                total += cost[i][mineralList[mIdx++]];
                if (total >= min) return total;
            }
        }
        return total;
    }
}

