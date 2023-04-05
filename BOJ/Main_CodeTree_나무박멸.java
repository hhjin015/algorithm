package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_CodeTree_나무박멸 {
    static int N, M, K, C, ans;
    static int[][] map, copyMap, herbicide;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    static class Tree {
        int x;
        int y;

        public Tree(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        copyMap = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == -1) map[i][j] = -100;
            }
        }

        for (int t = 1; t <= M; t++) {
            growTree();

            for (int i = 0; i < N; i++) {
                copyMap[i] = map[i].clone();
            }

            spreadTree();

            Tree spot = checkHerbicide();
            if (spot.x == N && spot.y == N) {
                System.out.println(ans);
                System.exit(0);
            }

            deleteHerbicide();
            spreadHerbicide(spot);
        }
        System.out.println(ans);
    }

    private static void growTree() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > 0) {
                    int cnt = 0;

                    for (int d = 0; d < 7; d += 2) {
                        int nr = i + dr[d];
                        int nc = j + dc[d];

                        if (isBoundary(nr, nc) && map[nr][nc] > 0) cnt++;
                    }
                    map[i][j] += cnt;
                }
            }
        }
    }

    private static void spreadTree() {
        Queue<Tree> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (copyMap[i][j] > 0) {
                    int cnt = 0;
                    for (int d = 0; d < 7; d += 2) {
                        int nr = i + dr[d];
                        int nc = j + dc[d];
                        if (isBoundary(nr, nc) && copyMap[nr][nc] == 0) {
                            cnt++;
                            q.add(new Tree(nr, nc));
                        }
                    }
                    if (cnt == 0) continue;
                    int num = copyMap[i][j] / cnt;

                    while (!q.isEmpty()) {
                        Tree t = q.poll();
                        map[t.x][t.y] += num;
                    }
                }
            }
        }
    }

    private static Tree checkHerbicide() {
        herbicide = new int[N][N];
        int max = Integer.MIN_VALUE;
        List<Tree> list = new ArrayList<>();
        Tree spot = new Tree(N, N);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int cnt = 0;
                if (map[i][j] > 0) {
                    cnt += map[i][j];

                    for (int d = 1; d < 8; d += 2) {
                        for (int k = 1; k <= K; k++) {
                            int nr = i + (dr[d] * k);
                            int nc = j + (dc[d] * k);
                            if (!isBoundary(nr, nc) || map[nr][nc] <= 0) break;
                            cnt += map[nr][nc];
                        }
                    }
                    herbicide[i][j] = cnt;
                    max = Math.max(max, cnt);
                }
            }
        }
        if (max == Integer.MIN_VALUE) return spot;

        ans += max;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (herbicide[i][j] == max) {
                    list.add(new Tree(i, j));
                }
            }
        }
        if (list.size() > 1) {
            for (Tree tree : list) {
                if (spot.x == tree.x && spot.y > tree.y) spot = tree;
                else if (spot.x > tree.x) spot = tree;
            }
        } else spot = list.get(0);

        return spot;
    }

    private static void spreadHerbicide(Tree tree) {
        map[tree.x][tree.y] = -C;
        for (int d = 1; d < 8; d += 2) {
            for (int k = 1; k <= K; k++) {
                int nr = tree.x + (dr[d] * k);
                int nc = tree.y + (dc[d] * k);
                if (!isBoundary(nr, nc) || map[nr][nc] == -100) break;
                if (map[nr][nc] <= 0) {
                    map[nr][nc] = -C;
                    break;
                } else map[nr][nc] = -C;
            }
        }
    }

    private static void deleteHerbicide() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] < 0 && map[i][j] > -100) map[i][j]++;
            }
        }
    }

    private static boolean isBoundary(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}