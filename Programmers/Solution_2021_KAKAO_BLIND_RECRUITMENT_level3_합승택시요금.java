package Programmers;

import java.util.Arrays;

public class Solution_2021_KAKAO_BLIND_RECRUITMENT_level3_합승택시요금 {
    public static void main(String[] args) {
        System.out.println(Solution(6, 4, 6, 2, new int[][]{{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}}));
        System.out.println(Solution(7, 3, 4, 1, new int[][]{{5, 7, 9}, {4, 6, 4}, {3, 6, 1}, {3, 2, 3}, {2, 1, 6}}));
        System.out.println(Solution(6, 4, 5, 6, new int[][]{{2, 6, 6}, {6, 3, 7}, {4, 6, 7}, {6, 5, 11}, {2, 5, 12}, {5, 3, 20}, {2, 4, 8}, {4, 3, 9}}));
    }

    private static int Solution(int n, int s, int a, int b, int[][] fares) {
        int[][] map = new int[n][n];
        int INF = 20000000;

        for (int i = 0; i < n; i++) {
            Arrays.fill(map[i], INF);
        }

        for (int i = 0; i < fares.length; i++) {
            int from = fares[i][0] - 1;
            int to = fares[i][1] - 1;
            int cost = fares[i][2];

            map[from][to] = cost;
            map[to][from] = cost;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] > map[i][k] + map[k][j])
                        map[i][j] = map[i][k] + map[k][j];
                }
            }
        }

        int min = map[s - 1][a - 1] + map[s - 1][b - 1];
        for (int i = 0; i < n; i++) {
            if (i == s - 1) continue;

            int cost = map[s - 1][i];
            if (i != a - 1) cost += map[i][a - 1];
            if (i != b - 1) cost += map[i][b - 1];

            min = Math.min(min, cost);
        }

        return min;
    }
}
