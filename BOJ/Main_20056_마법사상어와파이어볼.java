package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_20056_마법사상어와파이어볼 {
    static int N, M, K, ans;
    static List<Fireball> fireballs;
    static Queue<Fireball>[][] map;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    static class Fireball {
        int r;
        int c;
        int weight;
        int speed;
        int d;

        public Fireball(int r, int c, int weight, int speed, int d) {
            this.r = r;
            this.c = c;
            this.weight = weight;
            this.speed = speed;
            this.d = d;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        fireballs = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            fireballs.add(new Fireball(r, c, m, s, d));
        }

        map = new Queue[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new LinkedList<>();
            }
        }

        for (int t = 1; t <= K; t++) {
            move();
            spreadFireballs();
        }
        getTotalWeight();
        System.out.println(ans);
    }

    private static void move() {
        for (int i = 0; i < fireballs.size(); i++) {
            Fireball f = fireballs.get(i);
            f.r = (N + f.r + dr[f.d] * (f.speed % N)) % N;
            f.c = (N + f.c + dc[f.d] * (f.speed % N)) % N;
            map[f.r][f.c].add(f);
        }
    }

    private static void spreadFireballs() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].size() > 1) {
                    Queue<Fireball> q = map[i][j];
                    int size = q.size();
                    int weightCnt = 0;
                    int speedCnt = 0;
                    int evenCnt = 0;
                    int oddCnt = 0;
                    while (!q.isEmpty()) {
                        Fireball f = q.poll();
                        weightCnt += f.weight;
                        speedCnt += f.speed;
                        if (f.d % 2 == 0) evenCnt++;
                        else oddCnt++;
                        fireballs.remove(f);
                    }
                    int weight = weightCnt / 5;
                    if (weight == 0) continue;
                    int speed = speedCnt / size;

                    if (oddCnt == size || evenCnt == size) {
                        for (int k = 0; k < 7; k += 2) {
                            fireballs.add(new Fireball(i, j, weight, speed, k));
                        }
                    } else {
                        for (int k = 1; k < 8; k += 2) {
                            fireballs.add(new Fireball(i, j, weight, speed, k));
                        }
                    }
                } else map[i][j].clear();
            }
        }
    }

    private static void getTotalWeight() {
        for (int i = 0; i < fireballs.size(); i++) {
            Fireball f = fireballs.get(i);
            ans += f.weight;
        }
    }
}

