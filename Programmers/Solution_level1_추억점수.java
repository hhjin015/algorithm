package Programmers;

import java.util.Arrays;
import java.util.HashMap;

public class Solution_level1_추억점수 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(Solution(new String[]{"may", "kein", "kain", "radi"}, new int[]{5, 10, 1, 3},
                new String[][]{{"may", "kein", "kain", "radi"}, {"may", "kein", "brin", "deny"}, {"kon", "kain", "may", "coni"}})));
        System.out.println(Arrays.toString(Solution(new String[]{"kali", "mari", "don"}, new int[]{11, 1, 55},
                new String[][]{{"kali", "mari", "don"}, {"pony", "tom", "teddy"}, {"con", "mona", "don"}})));
        System.out.println(Arrays.toString(Solution(new String[]{"may", "kein", "kain", "radi"}, new int[]{5, 10, 1, 3},
                new String[][]{{"may"}, {"kein", "deny", "may"}, {"kon", "coni"}})));
    }

    private static int[] Solution(String[] name, int[] yearning, String[][] photo) {
        HashMap<String, Integer> hm = new HashMap<>();

        for (int i = 0; i < name.length; i++) {
            hm.put(name[i], yearning[i]);
        }

        int[] ans = new int[photo.length];
        for (int i = 0; i < photo.length; i++) {
            int cnt = 0;
            for (int j = 0; j < photo[i].length; j++) {
                if (hm.get(photo[i][j]) != null) {
                    cnt += hm.get(photo[i][j]);
                }
            }
            ans[i] = cnt;
        }

        return ans;
    }
}
