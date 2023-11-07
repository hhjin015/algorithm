package Programmers;

import java.util.*;

public class Solution_level1_달리기경주 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(Solution(new String[]{"mumu", "soe", "poe", "kai", "mine"}, new String[]{"kai", "kai", "mine", "mine"})));
    }

    private static String[] Solution(String[] players, String[] callings) {
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < players.length; i++) {
            map.put(players[i], i);
        }

        for (int i = 0; i < callings.length; i++) {
            int n = map.get(callings[i]);
            String temp = players[n - 1];

            players[n - 1] = callings[i];
            players[n] = temp;

            map.replace(temp, n);
            map.replace(callings[i], n - 1);
        }

        return players;
    }
}
