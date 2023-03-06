package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main_21608_상어초등학교 {
    static int N;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[] sequence = new int[N * N];
        int[][] map = new int[N][N];
        int ans = 0;

        HashMap<Integer, int[]> hashMap = new HashMap<>();
        for (int i = 0; i < N * N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int key = Integer.parseInt(st.nextToken());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int n3 = Integer.parseInt(st.nextToken());
            int n4 = Integer.parseInt(st.nextToken());

            sequence[i] = key;
            hashMap.put(key, new int[]{n1, n2, n3, n4});
        }

        // 자리 정하기
        for (int t = 0; t < N * N; t++) {
            int student = sequence[t];
            int[] arr = hashMap.get(student);

            int favorite = -1;
            int space = -1;
            int x = 0;
            int y = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] != 0) continue;

                    int tempFavorite = 0;
                    int tempSpace = 0;
                    for (int d = 0; d < 4; d++) {
                        int nr = i + dr[d];
                        int nc = j + dc[d];

                        if (isBoundary(nr, nc)) {
                            int n = map[nr][nc];
                            if (n == arr[0] || n == arr[1] || n == arr[2] || n == arr[3]) {
                                tempFavorite++;
                            } else if (n == 0) {
                                tempSpace++;
                            }
                        }
                    }
                    if (tempFavorite > favorite) {
                        favorite = tempFavorite;
                        space = tempSpace;
                        x = i;
                        y = j;
                    } else if (tempFavorite == favorite && tempSpace > space) {
                        space = tempSpace;
                        x = i;
                        y = j;
                    }
                }
            }
            map[x][y] = student;
        }

        // 만족도 구하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int[] arr = hashMap.get(map[i][j]);
                int cnt = 0;

                for (int d = 0; d < 4; d++) {
                    int nr = i + dr[d];
                    int nc = j + dc[d];

                    if (isBoundary(nr, nc)) {
                        int n = map[nr][nc];
                        if (n == arr[0] || n == arr[1] || n == arr[2] || n == arr[3]) cnt++;
                    }
                }
                switch (cnt) {
                    case 1:
                        ans += 1;
                        break;
                    case 2:
                        ans += 10;
                        break;
                    case 3:
                        ans += 100;
                        break;
                    case 4:
                        ans += 1000;
                        break;
                }
            }
        }
        System.out.println(ans);
    }

    private static boolean isBoundary(int r, int c) {
        return r >= 0 && c >= 0 && r < N && c < N;
    }
}
