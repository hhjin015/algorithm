package Programmers;

public class Solution_그래프_level3_순위 {
    public static void main(String[] args) {
        System.out.println(Solution(5, new int[][]{{4, 3}, {4, 2}, {3, 2}, {1, 2}, {2, 5}}));
    }

    private static int Solution(int n, int[][] results) {
        boolean[][] map = new boolean[n][n];

        for (int i = 0; i < results.length; i++) {
            int a = results[i][0] - 1;
            int b = results[i][1] - 1;

            map[a][b] = true;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][k] && map[k][j]) map[i][j] = true;
                }
            }
        }

        int[] count = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j]) {
                    ++count[i];
                    ++count[j];
                }
            }
        }

        int ans = 0;
        for (int i : count) {
            if (i == n - 1) ++ans;
        }

        return ans;
    }
}
