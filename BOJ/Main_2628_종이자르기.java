package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_2628_종이자르기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(br.readLine());

        List<Integer> tempRow = new ArrayList<>();
        List<Integer> tempCol = new ArrayList<>();
        List<Integer> rowList = new ArrayList<>();
        List<Integer> colList = new ArrayList<>();

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            if (dir == 0) tempRow.add(num);
            else tempCol.add(num);
        }

        getList(N, tempRow, rowList);
        getList(M, tempCol, colList);

        int max = Integer.MIN_VALUE;
        if (rowList.size() == 0) {
            for (int i = 1; i < colList.size(); i++) {
                max = Math.max(N * colList.get(i), max);
            }
        } else if (colList.size() == 0) {
            for (int i = 1; i < rowList.size(); i++) {
                max = Math.max(N * rowList.get(i), max);
            }
        } else {
            for (int i = 0; i < rowList.size(); i++) {
                for (int j = 0; j < colList.size(); j++) {
                    int n = rowList.get(i) * colList.get(j);
                    max = Math.max(n, max);
                }
            }
        }
        System.out.println(max);
    }

    private static void getList(int m, List<Integer> temp, List<Integer> list) {
        if (temp.size() > 0) {
            Collections.sort(temp);

            list.add(temp.get(0));

            for (int i = 1; i < temp.size(); i++) {
                list.add(temp.get(i) - temp.get(i - 1));
            }

            list.add(m - temp.get(temp.size() - 1));
        }
    }
}
