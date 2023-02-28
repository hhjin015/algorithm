package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_15686_치킨배달 {
    static int N, M, chickenCnt, deleteCnt, chickenRoad;
    static int[][] map, copyMap;
    static List<Chicken> allChickens;
    static Chicken[] selectedChickens;
    static int min = Integer.MAX_VALUE;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        copyMap = new int[N][N];

        chickenCnt = 0;
        allChickens = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                copyMap[i][j] = map[i][j];
                if (map[i][j] == 2) {
                    chickenCnt++;
                    allChickens.add(new Chicken(i, j, 0));
                }
            }
        }
        deleteCnt = chickenCnt - M;
        selectedChickens = new Chicken[deleteCnt];

        combination(0, 0);
        System.out.println(min);
    }

    static class Chicken {
        int x;
        int y;
        int dis;

        public Chicken(int x, int y, int dis) {
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
    }

    private static void combination(int cnt, int start) {
        if (cnt == deleteCnt) {
            deleteChickens();
            return;
        }

        for (int i = start; i < chickenCnt; i++) {
            selectedChickens[cnt] = allChickens.get(i);
            combination(cnt + 1, i + 1);
        }
    }

    private static void deleteChickens() {
        chickenRoad = 0;
        for (int i = 0; i < deleteCnt; i++) {
            Chicken c = selectedChickens[i];
            map[c.x][c.y] = 0;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 1) {
                    bfs(i, j);
                }
            }
        }
        min = Math.min(min, chickenRoad);

        for (int i = 0; i < N; i++) {
            map[i] = copyMap[i].clone();
        }
    }

    private static void bfs(int r, int c) {
        boolean[][] visited = new boolean[N][N];
        Queue<Chicken> q = new LinkedList<>();
        q.add(new Chicken(r, c, 0));
        visited[r][c] = true;

        while (!q.isEmpty()) {
            Chicken chicken = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = chicken.x + dr[d];
                int nc = chicken.y + dc[d];
                if (isBoundary(nr, nc) && !visited[nr][nc]) {
                    if (map[nr][nc] == 2) {
                        chickenRoad += chicken.dis + 1;
                        return;
                    } else {
                        q.add(new Chicken(nr, nc, chicken.dis + 1));
                        visited[nr][nc] = true;
                    }
                }
            }
        }
    }

    private static boolean isBoundary(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
