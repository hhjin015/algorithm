package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_16234_인구이동 {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int N, L, R;
    static int[][] map;
    static boolean[][] visited;
    static List<Country> list;

    static class Country {
        int r;
        int c;

        public Country(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int days = 0;
        while (true) {
            boolean flag = false;
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        int sum = move(i, j);
                        if (list.size() > 1) {
                            movePopulation(sum);
                            flag = true;
                        }
                    }
                }
            }
            if (!flag) {
                System.out.println(days);
                break;
            }
            days++;
        }
    }

    private static int move(int i, int j) {
        Queue<Country> q = new LinkedList<>();
        list = new ArrayList<>();

        q.offer(new Country(i, j));
        list.add(new Country(i, j));
        visited[i][j] = true;

        int sum = map[i][j];
        while (!q.isEmpty()) {
            Country country = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = country.r + dr[d];
                int nc = country.c + dc[d];

                if (nr >= 0 && nc >= 0 && nr < N && nc < N && !visited[nr][nc]) {
                    int diff = Math.abs(map[nr][nc] - map[country.r][country.c]);
                    if (diff >= L && diff <= R) {
                        q.offer(new Country(nr, nc));
                        list.add(new Country(nr, nc));
                        visited[nr][nc] = true;

                        sum += map[nr][nc];
                    }
                }
            }
        }
        return sum;
    }

    private static void movePopulation(int sum) {
        int avg = sum / list.size();
        for (int i = 0; i < list.size(); i++) {
            Country c = list.get(i);

            map[c.r][c.c] = avg;
        }
    }
}
