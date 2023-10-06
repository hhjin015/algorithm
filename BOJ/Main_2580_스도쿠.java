package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2580_스도쿠 {
    static int[][] arr = new int[9][9];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sudoku(0, 0);
    }

    private static void sudoku(int r, int c) {
        if (c == 9) {
            sudoku(r + 1, 0);
            return;
        }

        if (r == 9) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(arr[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
            System.exit(0);
        }

        if (arr[r][c] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (possible(r, c, i)) {
                    arr[r][c] = i;
                    sudoku(r, c + 1);
                }
            }
            arr[r][c] = 0;
            return;
        }

        sudoku(r, c + 1);
    }

    private static boolean possible(int r, int c, int value) {
        for (int i = 0; i < 9; i++) {
            if (arr[i][c] == value) return false;
        }

        for (int i = 0; i < 9; i++) {
            if (arr[r][i] == value) return false;
        }

        int startRow = (r / 3) * 3;
        int startCol = (c / 3) * 3;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (arr[i][j] == value) return false;
            }
        }
        return true;
    }
}
