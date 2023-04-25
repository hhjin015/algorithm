package Programmers;

import java.util.*;

public class Solution_2021_DevMatching_level2_행렬테두리회전하기 {
    static int[][] map;
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};

    public static void main(String[] args) {
        System.out.println(Arrays.toString(Solution(6, 6, new int[][]{{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}})));
        System.out.println(Arrays.toString(Solution(3, 3, new int[][]{{1, 1, 2, 2}, {1, 2, 2, 3}, {2, 1, 3, 2}, {2, 2, 3, 3}})));
        System.out.println(Arrays.toString(Solution(100, 97, new int[][]{{1, 1, 100, 97}})));
    }

    private static int[] Solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        map = new int[rows][columns];

        int cnt = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                map[i][j] = ++cnt;
            }
        }

        for (int i = 0; i < queries.length; i++) {
            answer[i] = turn(queries[i][0] - 1, queries[i][1] - 1, queries[i][2] - 1, queries[i][3] - 1);
        }

        return answer;
    }

    private static int turn(int sr, int sc, int er, int ec) {
        int d = 0;
        int r = sr;
        int c = sc;
        int me = map[r][c];
        int min = Integer.MAX_VALUE;

        while (true) {
            int nr = sr + dr[d];
            int nc = sc + dc[d];

            if (nc > ec || nr > er || nc < c || nr < r) d++;

            sr = sr + dr[d];
            sc = sc + dc[d];

            int next = map[sr][sc];
            map[sr][sc] = me;
            me = next;

            min = Math.min(min, map[sr][sc]);

            if (sr == r && sc == c) break;
        }
        return min;
    }
}
