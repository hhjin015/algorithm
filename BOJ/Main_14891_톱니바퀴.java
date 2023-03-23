package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_14891_톱니바퀴 {
    static int[][] gear;
    static int[] dir;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        gear = new int[4][8];
        for (int i = 0; i < 4; i++) {
            String str = br.readLine();
            for (int j = 0; j < 8; j++) {
                gear[i][j] = str.charAt(j) - '0';
            }
        }

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());

            dir = new int[4];
            dir[n] = d;
            checkDir(n);
            turnGear();
        }

        int ans = 0;
        if (gear[0][0] == 1) ans += 1;
        if (gear[1][0] == 1) ans += 2;
        if (gear[2][0] == 1) ans += 4;
        if (gear[3][0] == 1) ans += 8;

        System.out.println(ans);
    }

    private static void checkDir(int gearNum) {
        // 왼쪽 체크
        for (int i = gearNum - 1; i >= 0; i--) {
            if (gear[i][2] != gear[i + 1][6]) {
                dir[i] = -dir[i + 1];
            } else break;
        }

        // 오른쪽 체크
        for (int i = gearNum + 1; i < 4; i++) {
            if (gear[i][6] != gear[i - 1][2]) {
                dir[i] = -dir[i - 1];
            } else break;
        }
    }

    private static void turnGear() {
        for (int i = 0; i < 4; i++) {
            int temp = 0;

            // 시계 방향
            if (dir[i] == 1) {
                temp = gear[i][7];
                for (int j = 7; j > 0; j--) {
                    gear[i][j] = gear[i][j - 1];
                }
                gear[i][0] = temp;
            }

            // 반시계 방향
            if (dir[i] == -1) {
                temp = gear[i][0];
                for (int j = 0; j < 7; j++) {
                    gear[i][j] = gear[i][j + 1];
                }
                gear[i][7] = temp;
            }
        }
    }
}
