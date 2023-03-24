package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_9663_NQueen {
    static int N, ans;
    static int[] col;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        ans = 0;
        col = new int[N + 1];

        setQueen(1);
        System.out.println(ans);
    }

    public static void setQueen(int rowNo) {    // rowNo: 퀸을 두어야 하는 현재 행
        if (!isAvailable(rowNo - 1)) return;

        if (rowNo > N) {
            ans++;
            return;
        }

        // 1열 ~ N열까지 퀸 놓아보기
        for (int i = 1; i <= N; i++) {
            col[rowNo] = i;
            setQueen(rowNo + 1);
        }
    }

    public static boolean isAvailable(int rowNo) {
        for (int i = 1; i < rowNo; i++) {
            if (col[rowNo] == col[i] || rowNo - i == Math.abs(col[rowNo] - col[i])) return false;
        }
        return true;
    }
}

