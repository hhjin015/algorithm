package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_19236_청소년상어 {
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};
    static int max;

    static class Fish implements Comparable<Fish> {
        int x, y;
        int dir;
        int id;
        boolean isAlive;

        public Fish(int x, int y, int dir, int id, boolean isAlive) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.id = id;
            this.isAlive = isAlive;
        }

        @Override
        public int compareTo(Fish o) {
            return this.id - o.id;
        }
    }

    static class Shark {
        int x, y;
        int dir;
        int eatCnt;

        public Shark(int x, int y, int dir, int eatCnt) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.eatCnt = eatCnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] map = new int[4][4];
        List<Fish> fishes = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 4; j++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken()) - 1;

                map[i][j] = a;
                fishes.add(new Fish(i, j, b, a, true));
            }
        }

        Collections.sort(fishes);

        Fish fish = fishes.get(map[0][0] - 1);
        fish.isAlive = false;
        Shark shark = new Shark(0, 0, fish.dir, fish.id);
        map[0][0] = -1;

        dfs(map, fishes, shark);

        System.out.println(max);
    }

    private static void dfs(int[][] map, List<Fish> fishes, Shark shark) {
        max = Math.max(max, shark.eatCnt);

        moveFish(map, fishes);

        for (int i = 1; i < 4; i++) {
            int nr = shark.x + dr[shark.dir] * i;
            int nc = shark.y + dc[shark.dir] * i;

            if (isOutOfRange(nr, nc) || map[nr][nc] == 0) continue;

            int[][] newMap = copyMap(map);
            List<Fish> newFishes = copyList(fishes);

            newMap[shark.x][shark.y] = 0;
            Fish fish = newFishes.get(map[nr][nc] - 1);
            Shark newShark = new Shark(nr, nc, fish.dir, shark.eatCnt + fish.id);
            fish.isAlive = false;
            newMap[fish.x][fish.y] = -1;

            dfs(newMap, newFishes, newShark);
        }
    }

    private static void moveFish(int[][] map, List<Fish> fishes) {
        for (int i = 0; i < 16; i++) {
            Fish fish = fishes.get(i);
            if (!fish.isAlive) continue;

            for (int d = 0; d < 8; d++) {
                int nr = fish.x + dr[fish.dir];
                int nc = fish.y + dc[fish.dir];

                if (isOutOfRange(nr, nc) || map[nr][nc] == -1) {
                    fish.dir = (fish.dir + 1) % 8;
                    continue;
                }

                if (map[nr][nc] == 0) {
                    map[fish.x][fish.y] = 0;
                } else {
                    Fish temp = fishes.get(map[nr][nc] - 1);

                    temp.x = fish.x;
                    temp.y = fish.y;
                    map[fish.x][fish.y] = temp.id;

                }
                map[nr][nc] = fish.id;
                fish.x = nr;
                fish.y = nc;
                break;
            }
        }
    }


    private static boolean isOutOfRange(int r, int c) {
        return r < 0 || c < 0 || r >= 4 || c >= 4;
    }

    private static int[][] copyMap(int[][] original) {
        int[][] map = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                map[i][j] = original[i][j];
            }
        }

        return map;
    }

    private static List<Fish> copyList(List<Fish> original) {
        List<Fish> list = new ArrayList<>();
        for (int i = 0; i < 16; i++) {
            Fish f = original.get(i);
            list.add(new Fish(f.x, f.y, f.dir, f.id, f.isAlive));
        }

        return list;
    }
}

