package Programmers;

public class Solution_2022_KAKAO_BLIND_RECRUITMENT_level3_파괴되지않은건물 {
    public static void main(String[] args) {
        System.out.println(Solution(
                new int[][]{{5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}, {5, 5, 5, 5, 5}},
                new int[][]{{1, 0, 0, 3, 4, 4}, {1, 2, 0, 2, 3, 2}, {2, 1, 0, 3, 1, 2}, {1, 0, 1, 3, 3, 1}}));
        System.out.println(Solution(
                new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}},
                new int[][]{{1, 1, 1, 2, 2, 4}, {1, 0, 0, 1, 1, 2}, {2, 2, 0, 2, 0, 100}}));
    }

    private static int Solution(int[][] board, int[][] skill) {
        int n = board.length;
        int m = board[0].length;

        int[][] map = new int[n][m];

        for (int i = 0; i < skill.length; i++) {
            int sr = skill[i][1];
            int sc = skill[i][2];
            int er = skill[i][3];
            int ec = skill[i][4];
            int degree = skill[i][5];

            if (skill[i][0] == 1) degree *= -1;

            map[sr][sc] += degree;
            if (!isOutOfRange(sr, ec + 1, n, m)) map[sr][ec + 1] -= degree;
            if (!isOutOfRange(er + 1, sc, n, m)) map[er + 1][sc] -= degree;
            if (!isOutOfRange(er + 1, ec + 1, n, m)) map[er + 1][ec + 1] += degree;

        }

        // 누적합 계산 (행 기준)
        for (int i = 0; i < n; i++) {
            int temp = 0;
            for (int j = 0; j < m; j++) {
                map[i][j] += temp;
                temp = map[i][j];
            }
        }

        // 누적합 계산 (열 기준)
        for (int i = 0; i < m; i++) {
            int temp = 0;
            for (int j = 0; j < n; j++) {
                map[j][i] += temp;
                temp = map[j][i];
            }
        }

        // 누적합 적용
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] + map[i][j] > 0) ++answer;
            }
        }

        return answer;
    }

    private static boolean isOutOfRange(int r, int c, int n, int m) {
        return r < 0 || c < 0 || r >= n || c >= m;
    }
}
