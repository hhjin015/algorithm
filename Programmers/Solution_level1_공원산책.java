package Programmers;

import java.util.Arrays;

public class Solution_level1_공원산책 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(Solution(new String[]{"SOO", "OOO", "OOO"}, new String[]{"E 2", "S 2", "W 1"})));
        System.out.println(Arrays.toString(Solution(new String[]{"SOO", "OXX", "OOO"}, new String[]{"E 2", "S 2", "W 1"})));
        System.out.println(Arrays.toString(Solution(new String[]{"OSO", "OOO", "OXO", "OOO"}, new String[]{"E 2", "S 3", "W 1"})));
    }

    private static int[] Solution(String[] park, String[] routes) {
        char[][] map = new char[park.length][park[0].length()];
        int[] start = new int[2];

        for (int i = 0; i < park.length; i++) {
            map[i] = park[i].toCharArray();
            for (int j = 0; j < park[0].length(); j++) {
                if (map[i][j] == 'S') {
                    start[0] = i;
                    start[1] = j;
                }
            }
        }

        int[][] route = new int[routes.length][2];
        for (int i = 0; i < routes.length; i++) {
            switch (routes[i].split(" ")[0]) {
                case "N":
                    route[i][0] = 0;
                    break;
                case "E":
                    route[i][0] = 1;
                    break;
                case "S":
                    route[i][0] = 2;
                    break;
                case "W":
                    route[i][0] = 3;
            }

            route[i][1] = Integer.parseInt(routes[i].split(" ")[1]);
        }

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        for (int i = 0; i < route.length; i++) {
            int nr = start[0];
            int nc = start[1];
            boolean flag = false;

            for (int d = 0; d < route[i][1]; d++) {
                nr += dr[route[i][0]];
                nc += dc[route[i][0]];

                if (isOutOfBounds(nr, nc, park.length, park[0].length()) || map[nr][nc] == 'X') {
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                start[0] = nr;
                start[1] = nc;
            }
        }

        return start;
    }

    private static boolean isOutOfBounds(int r, int c, int n, int m) {
        return r < 0 || c < 0 || r >= n || c >= m;
    }
}
