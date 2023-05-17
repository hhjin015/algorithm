package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_3020_개똥벌레 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[] stalagmite = new int[N / 2];   // 석순
        int[] stalactite = new int[N / 2];   // 종유석

        for (int i = 0; i < N / 2; i++) {
            int a = Integer.parseInt(br.readLine());
            int b = Integer.parseInt(br.readLine());
            stalagmite[i] = a;
            stalactite[i] = b;
        }

        Arrays.sort(stalagmite);
        Arrays.sort(stalactite);

        int min = N;
        int cnt = 0;
        for (int i = 1; i < H + 1; i++) {
            int conflict = binarySearch(0, N / 2, i, stalagmite) + binarySearch(0, N / 2, H - i + 1, stalactite);
            if (min == conflict) {
                cnt++;
                continue;
            }
            if (min > conflict) {
                min = conflict;
                cnt = 1;
            }
        }
        System.out.println(min + " " + cnt);
    }

    private static int binarySearch(int left, int right, int h, int[] arr) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] >= h) right = mid;
            else left = mid + 1;
        }
        return arr.length - right;
    }
}
