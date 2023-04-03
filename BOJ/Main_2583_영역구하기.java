package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_2583_영역구하기 {
    static int M, N, K, spaceCnt;
    static boolean[][] visited;
    static List<Integer> list;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        visited = new boolean[M][N];
        list = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int c1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());

            makeSquare(r1, c1, r2, c2);
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    spaceCnt = 0;
                    checkEmptySpace(i, j);
                    list.add(spaceCnt);
                }
            }
        }
        System.out.println(list.size());
        Collections.sort(list);

        for (int n : list) {
            System.out.print(n + " ");
        }
    }

    private static void makeSquare(int r1, int c1, int r2, int c2) {
        for (int i = r1; i < r2; i++) {
            for (int j = c1; j < c2; j++) {
                visited[i][j] = true;
            }
        }
    }

    private static void checkEmptySpace(int r, int c) {
        visited[r][c] = true;
        spaceCnt += 1;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (isBoundary(nr, nc) && !visited[nr][nc]) {
                checkEmptySpace(nr, nc);
            }
        }
    }

    private static boolean isBoundary(int r, int c) {
        return r >= 0 && c >= 0 && r < M && c < N;
    }
}
