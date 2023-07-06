package Programmers;

import java.util.*;

public class Solution_깊이너비우선탐색_level3_여행경로 {
    static ArrayList<String> list;
    static boolean[] useTickets;

    public static void main(String[] args) {
        String[] arr = Solution(new String[][]{{"ICN", "BOO"}, {"ICN", "COO"}, {"COO", "DOO"}, {"DOO", "COO"}, {"BOO", "DOO"}, {"DOO", "BOO"}, {"BOO", "ICN"}, {"COO", "BOO"}});
        System.out.println(Arrays.toString(arr));
    }

    static String[] Solution(String[][] tickets) {
        useTickets = new boolean[tickets.length];
        list = new ArrayList<>();

        dfs(0, "ICN", "ICN", tickets);

        Collections.sort(list);

        return list.get(0).split(" ");
    }

    static void dfs(int depth, String start, String path, String[][] tickets) {
        if (depth == tickets.length) {
            list.add(path);
            return;
        }

        for (int i = 0; i < useTickets.length; i++) {
            if (!useTickets[i] && start.equals(tickets[i][0])) {
                useTickets[i] = true;
                dfs(depth + 1, tickets[i][1], path + " " + tickets[i][1], tickets);
                useTickets[i] = false;
            }
        }
    }
}
