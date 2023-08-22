package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_16507_어두운건무서워 {
    static int R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int[][] map = new int[R][C];
        int[][] rowMap = new int[R][C];
        int[][] colMap = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < R; i++) {
            int temp = 0;
            for (int j = 0; j < C; j++) {
                rowMap[i][j] = map[i][j] + temp;
                temp = rowMap[i][j];
            }
        }

        for (int i = 0; i < C; i++) {
            int temp = 0;
            for (int j = 0; j < R; j++) {
                colMap[j][i] = map[j][i] + temp;
                temp = colMap[j][i];
            }
        }

        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(new int[]{
                    Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()) - 1,
                    Integer.parseInt(st.nextToken()) - 1});
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            int[] arr = list.get(i);
            int sr = arr[0];
            int sc = arr[1];
            int er = arr[2];
            int ec = arr[3];

            int cnt = (Math.abs(sr - er) + 1) * (Math.abs(sc - ec) + 1);
            if (cnt == 1) {
                sb.append(map[sr][sc]).append("\n");
                continue;
            }

            if (Math.abs(sr - er) > 0) {
                int a = 0;
                for (int j = sc; j <= ec; j++) {
                    a += colMap[er][j];
                }
                if (sr - 1 < 0) sb.append(a / cnt).append("\n");
                else {
                    int b = 0;
                    for (int j = sc; j <= ec; j++) {
                        b += colMap[sr - 1][j];
                    }
                    sb.append((a - b) / cnt).append("\n");
                }
            } else {
                if (isOutOfBounds(er, sc - 1)) sb.append(rowMap[er][ec] / cnt).append("\n");
                else sb.append((rowMap[er][ec] - rowMap[er][sc - 1]) / cnt).append("\n");
            }
        }
        System.out.println(sb);

    }

    private static boolean isOutOfBounds(int r, int c) {
        return r < 0 || c < 0 || r >= R || c >= C;
    }
}
