package Programmers;

import java.util.LinkedList;
import java.util.Queue;

public class Solution_PCCP기출문제_level2_2번_석유시추 {
    static int[][] oil, number;
    static int rSize, cSize;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) {
        System.out.println(solution(new int[][]{{0, 0, 0, 1, 1, 1, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 1, 0}, {1, 1, 1, 0, 0, 0, 0, 0}, {1, 1, 1, 0, 0, 0, 1, 1}}));
        System.out.println(solution(new int[][]{{1, 0, 1, 0, 1, 1}, {1, 0, 1, 0, 0, 0}, {1, 0, 1, 0, 0, 1}, {1, 0, 0, 1, 0, 0}, {1, 0, 0, 1, 0, 1}, {1, 0, 0, 0, 0, 0}, {1, 1, 1, 1, 1, 1}}));
    }

    private static int solution(int[][] land) {
        rSize = land.length;
        cSize = land[0].length;
        oil = new int[rSize][cSize];
        number = new int[rSize][cSize];
        int landCnt = 1;

        for (int i = 0; i < rSize; i++) {
            for (int j = 0; j < cSize; j++) {
                if (land[i][j] == 1) {
                    bfs(i, j, land, landCnt++);
                }
            }
        }


        int answer = 0;
        for (int i = 0; i < cSize; i++) {
            int count = 0;
            boolean[] lands = new boolean[landCnt];
            for (int j = 0; j < rSize; j++) {
                if (oil[j][i] != 0 && !lands[number[j][i]]) {
                    lands[number[j][i]] = true;
                    count += oil[j][i];
                }
            }
            answer = Math.max(answer, count);
        }

        return answer;
    }

    private static void bfs(int r, int c, int[][] land, int n) {
        Queue<int[]> q = new LinkedList<>();
        Queue<int[]> save = new LinkedList<>();

        q.offer(new int[]{r, c});
        save.offer(new int[]{r, c, n});
        land[r][c] = -1;
        int cnt = 1;

        while (!q.isEmpty()) {
            int[] p = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = p[0] + dr[d];
                int nc = p[1] + dc[d];

                if (isOutOfBounds(nr, nc) || land[nr][nc] == 0 || land[nr][nc] == -1) continue;

                q.offer(new int[]{nr, nc});
                save.offer(new int[]{nr, nc, n});
                land[nr][nc] = -1;
                cnt++;
            }
        }
        setOil(save, cnt);
    }

    private static boolean isOutOfBounds(int r, int c) {
        return r < 0 || c < 0 || r >= rSize || c >= cSize;
    }

    private static void setOil(Queue<int[]> q, int cnt) {
        while (!q.isEmpty()) {
            int[] p = q.poll();

            oil[p[0]][p[1]] = cnt;
            number[p[0]][p[1]] = p[2];
        }
    }
}
