package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_16236_아기상어 {
    static int N, time, tempSize;
    static int[][] map;
    static Spot babyShark;
    static List<Spot> minDisList;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static class Spot implements Comparable<Spot> {
        int x, y, num;

        public Spot(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }

        @Override
        public int compareTo(Spot o) {
            return this.x - o.x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) babyShark = new Spot(i, j, 2);
            }
        }

        while (true) {
            minDisList = new ArrayList<>();
            bfs();

            if (minDisList.size() == 0) break;
            else if (minDisList.size() == 1) eat(minDisList.get(0));
            else {
                List<Spot> highFish = getMostHighFish();
                if (highFish.size() == 1) eat(highFish.get(0));
                else {
                    Spot leftFish = getMostLeftFish(highFish);
                    eat(leftFish);
                }
            }
        }
        System.out.println(time);
    }

    private static void bfs() {
        Queue<Spot> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        int min = Integer.MAX_VALUE;

        visited[babyShark.x][babyShark.y] = true;
        q.add(new Spot(babyShark.x, babyShark.y, 0));

        while (!q.isEmpty()) {
            Spot spot = q.poll();

            if (spot.num > min) continue;

            for (int d = 0; d < 4; d++) {
                int nr = spot.x + dr[d];
                int nc = spot.y + dc[d];

                if (isOutOfRange(nr, nc) || map[nr][nc] > babyShark.num || visited[nr][nc]) continue;

                if (map[nr][nc] != 0 && map[nr][nc] < babyShark.num) {
                    if (min == Integer.MAX_VALUE) {
                        min = spot.num + 1;
                        minDisList.add(new Spot(nr, nc, min));
                    } else if (min == spot.num + 1) minDisList.add(new Spot(nr, nc, min));
                }

                visited[nr][nc] = true;
                q.add(new Spot(nr, nc, spot.num + 1));
            }
        }
    }

    private static void eat(Spot spot) {
        time += spot.num;
        tempSize++;

        if (tempSize == babyShark.num) {
            tempSize = 0;
            babyShark.num++;
        }

        map[babyShark.x][babyShark.y] = 0;
        map[spot.x][spot.y] = 9;

        babyShark.x = spot.x;
        babyShark.y = spot.y;
    }

    private static List<Spot> getMostHighFish() {
        Collections.sort(minDisList);
        List<Spot> list = new ArrayList<>();
        list.add(minDisList.get(0));

        for (int i = 1; i < minDisList.size(); i++) {
            if (minDisList.get(i).x == minDisList.get(0).x) list.add(minDisList.get(i));
            else break;
        }

        return list;
    }

    private static Spot getMostLeftFish(List<Spot> list) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++) {
            min = Math.min(list.get(i).y, min);
        }
        for (Spot spot : list) {
            if (spot.y == min) return spot;
        }

        return null;
    }

    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || c < 0 || r >= N || c >= N;
    }
}
