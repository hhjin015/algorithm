package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_CodeTree_싸움땅 {
    static int N, M, K;
    static Player curPlayer;
    static List<Integer>[][] guns;
    static int[] scores;
    static Player[] players;
    // 상 우 하 좌
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    static class Player {
        int x;
        int y;
        int d;
        int power;
        int gun;
        int num;

        public Player(int x, int y, int d, int power, int gun, int num) {
            this.x = x;
            this.y = y;
            this.d = d;
            this.power = power;
            this.gun = gun;
            this.num = num;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 격자 크기
        M = Integer.parseInt(st.nextToken());   // 플레이어 수
        K = Integer.parseInt(st.nextToken());   // 라운드 수

        guns = new ArrayList[N][N];
        players = new Player[M];
        scores = new int[M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                guns[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int n = Integer.parseInt(st.nextToken());
                if (n == 0) continue;
                guns[i][j].add(n);
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            players[i] = new Player(x, y, d, s, 0, i);
        }

        for (int t = 1; t <= K; t++) {
            for (int p = 0; p < M; p++) {
                curPlayer = players[p];
                move();
            }
        }
        for (int a : scores) {
            System.out.print(a+" ");
        }
    }

    private static void move() {
        int nr = curPlayer.x + dr[curPlayer.d];
        int nc = curPlayer.y + dc[curPlayer.d];
        if (!isBoundary(nr, nc)) {
            curPlayer.d = (curPlayer.d + 2) % 4;
            nr = curPlayer.x + dr[curPlayer.d];
            nc = curPlayer.y + dc[curPlayer.d];
        }

        curPlayer.x = nr;
        curPlayer.y = nc;

        boolean flag = false;
        for (int i = 0; i < M; i++) {
            if (i == curPlayer.num) continue;
            Player other = players[i];
            if (other.x == curPlayer.x && other.y == curPlayer.y) {
                fight(other);
                flag = true;
                break;
            }
        }
        if (!flag) checkGun(curPlayer);
    }

    private static void fight(Player other) {
        Player winner;
        Player loser;

        if (curPlayer.power + curPlayer.gun > other.power + other.gun) {
            winner = curPlayer;
            loser = other;
        } else if (curPlayer.power + curPlayer.gun == other.power + other.gun) {
            if (curPlayer.power > other.power) {
                winner = curPlayer;
                loser = other;
            } else {
                winner = other;
                loser = curPlayer;
            }
        } else {
            winner = other;
            loser = curPlayer;
        }
        scores[winner.num] += (winner.power + winner.gun) - (loser.power + loser.gun);;

        if (loser.gun > 0) {
            List gunList = guns[loser.x][loser.y];
            gunList.add(loser.gun);
            loser.gun = 0;
        }

        for (int n = 0; n < 4; n++) {
            int dir = (loser.d + n) % 4;
            int nr = loser.x + dr[dir];
            int nc = loser.y + dc[dir];

            if (!isBoundary(nr, nc) || isExistOther(loser, nr, nc)) continue;
            loser.x = nr;
            loser.y = nc;
            loser.d = dir;
            break;
        }
        checkGun(loser);
        checkGun(winner);
    }

    private static boolean isExistOther(Player p, int r, int c) {
        for (int i = 0; i < M; i++) {
            if (i == p.num) continue;
            Player other = players[i];
            if (r == other.x && c == other.y) return true;
        }
        return false;
    }

    private static void checkGun(Player p) {
        List gunList = guns[p.x][p.y];
        if (gunList.size() == 0) return;

        Collections.sort(gunList);
        if (p.gun == 0) {
            p.gun = (int) gunList.get(gunList.size() - 1);
            gunList.remove(gunList.size() - 1);
        } else if (p.gun < (int) gunList.get(gunList.size() - 1)) {
            int temp = p.gun;
            p.gun = (int) gunList.get(gunList.size() - 1);
            gunList.remove(gunList.size() - 1);
            gunList.add(temp);
        }
    }

    private static boolean isBoundary(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
