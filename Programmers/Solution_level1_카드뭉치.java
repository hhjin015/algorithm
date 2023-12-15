package Programmers;

public class Solution_level1_카드뭉치 {
    public static void main(String[] args) {
        System.out.println(solution(new String[]{"i", "drink", "water"}, new String[]{"want", "to"}, new String[]{"i", "want", "to", "drink", "water"}));
        System.out.println(solution(new String[]{"i", "water", "drink"}, new String[]{"want", "to"}, new String[]{"i", "want", "to", "drink", "water"}));
    }

    private static String solution(String[] cards1, String[] cards2, String[] goal) {
        boolean[] arr1 = new boolean[cards1.length];
        boolean[] arr2 = new boolean[cards2.length];

        for (int i = 0; i < goal.length; i++) {
            String str = goal[i];

            int idx1 = getIdx(str, cards1, arr1);
            if (idx1 != -1) {
                if (idx1 == 0) continue;
                if (!arr1[idx1 - 1]) return "No";
            }

            int idx2 = getIdx(str, cards2, arr2);
            if (idx2 != -1) {
                if (idx2 == 0) continue;
                if (!arr2[idx2 - 1]) return "No";
            }
        }

        return "Yes";
    }

    private static int getIdx(String target, String[] cards, boolean[] arr) {
        for (int i = 0; i < cards.length; i++) {
            if (cards[i].equals(target)) {
                arr[i] = true;
                return i;
            }
        }
        return -1;
    }
}
