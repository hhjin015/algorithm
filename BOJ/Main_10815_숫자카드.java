package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_10815_숫자카드 {
    static int[] existCards;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        existCards = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            existCards[i] = Integer.parseInt(st.nextToken());
        }

        int M = Integer.parseInt(br.readLine());
        int[] newCards = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            newCards[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(existCards);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            boolean isExist = binarySearch(newCards[i], 0, N - 1);
            if (isExist) sb.append(1).append(" ");
            else sb.append(0).append(" ");
        }
        System.out.println(sb);
    }

    private static boolean binarySearch(int key, int left, int right) {
        int mid;

        if (left <= right) {
            mid = (left + right) / 2;
            if (key == existCards[mid]) return true;
            else if (key > existCards[mid]) return binarySearch(key, mid + 1, right);
            else return binarySearch(key, left, mid - 1);
        }
        return false;
    }
}
