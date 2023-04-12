package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_16235_나무재테크 {
    static int N, M, K;
    static int[][] nouri, map;
    static List<Tree>[][] livingTree;
    static Queue<Tree> deadTree;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};

    static class Tree implements Comparable<Tree> {
        int x;
        int y;
        int age;

        public Tree(int x, int y, int age) {
            this.x = x;
            this.y = y;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return this.age - o.age;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        nouri = new int[N][N];
        livingTree = new ArrayList[N][N];
        deadTree = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                livingTree[i][j] = new ArrayList<>();
                nouri[i][j] = 5;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int age = Integer.parseInt(st.nextToken());
            livingTree[r][c].add(new Tree(r, c, age));
        }

        for (int t = 1; t <= K; t++) {
            spring();
            summer();
            autumn();
            winter();
        }

        System.out.println(getLivingTreeCount());
    }

    private static void spring() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (livingTree[i][j].size() == 0) continue;
                if (livingTree[i][j].size() == 1) {
                    if (nouri[i][j] - livingTree[i][j].get(0).age < 0) {
                        deadTree.add(livingTree[i][j].get(0));
                        livingTree[i][j].clear();
                    } else {
                        nouri[i][j] -= livingTree[i][j].get(0).age;
                        livingTree[i][j].get(0).age++;
                    }
                } else {
                    Collections.sort(livingTree[i][j]);
                    List<Integer> deleteList = new ArrayList<>();
                    for (int k = 0; k < livingTree[i][j].size(); k++) {
                        Tree tree = livingTree[i][j].get(k);
                        if (nouri[i][j] - tree.age < 0) {
                            deadTree.add(tree);
                            deleteList.add(k);
                        } else {
                            nouri[i][j] -= tree.age;
                            tree.age++;
                        }
                    }
                    for (int n = deleteList.size() - 1; n >= 0; n--) {
                        int idx = deleteList.get(n);
                        livingTree[i][j].remove(idx);
                    }
                }
            }
        }
    }

    private static void summer() {
        while (!deadTree.isEmpty()) {
            Tree tree = deadTree.poll();
            int r = tree.x;
            int c = tree.y;
            int age = tree.age / 2;

            nouri[r][c] += age;
        }
    }

    private static void autumn() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (livingTree[i][j].size() == 0) continue;
                for (int k = 0; k < livingTree[i][j].size(); k++) {
                    Tree tree = livingTree[i][j].get(k);
                    if (tree.age % 5 == 0) {
                        for (int d = 0; d < 8; d++) {
                            int nr = tree.x + dr[d];
                            int nc = tree.y + dc[d];
                            if (!isBoundary(nr, nc)) continue;
                            livingTree[nr][nc].add(new Tree(nr, nc, 1));
                        }
                    }
                }
            }
        }
    }

    private static void winter() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                nouri[i][j] += map[i][j];
            }
        }
    }

    private static int getLivingTreeCount() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cnt += livingTree[i][j].size();
            }
        }
        return cnt;
    }

    private static boolean isBoundary(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
