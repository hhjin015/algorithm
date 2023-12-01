package Programmers;

import java.util.*;

public class Solution_level2_무인도여행 {
    static int rSize, cSize;
    static int[][] map;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(new String[]{"X591X", "X1X5X", "X231X", "1XXX1"})));
        System.out.println(Arrays.toString(solution(new String[]{"XXX", "XXX", "XXX"})));
    }

    private static int[] solution(String[] maps) {
        rSize = maps.length;
        cSize = maps[0].length();
        map = new int[rSize][cSize];

        for (int i = 0; i < rSize; i++) {
            for (int j = 0; j < cSize; j++) {
                if (maps[i].charAt(j) != 'X') map[i][j] = maps[i].charAt(j) - '0';
                else map[i][j] = 0;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < rSize; i++) {
            for (int j = 0; j < cSize; j++) {
                if (map[i][j] != 0 && map[i][j] != -1) {
                    list.add(bfs(i, j));
                }
            }
        }

        if (list.size() == 0) return new int[]{-1};
        else {
            Collections.sort(list);
            int[] answer = new int[list.size()];

            for (int i = 0; i < list.size(); i++) {
                answer[i] = list.get(i);
            }
            return answer;
        }
    }

    private static int bfs(int r, int c) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{r, c});
        int island = map[r][c];
        map[r][c] = -1;

        while (!q.isEmpty()) {
            int[] p = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = p[0] + dr[d];
                int nc = p[1] + dc[d];

                if (isOutOfBounds(nr, nc) || map[nr][nc] == 0 || map[nr][nc] == -1) continue;

                q.offer(new int[]{nr, nc});
                island += map[nr][nc];
                map[nr][nc] = -1;
            }
        }
        return island;
    }

    private static boolean isOutOfBounds(int r, int c) {
        return r < 0 || c < 0 || r >= rSize || c >= cSize;
    }
}