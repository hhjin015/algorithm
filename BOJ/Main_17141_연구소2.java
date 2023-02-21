package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_17141_연구소2 {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static List<Node> allVirus;
    static Node[] selVirus;
    static List<Node[]> list;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   //크기
        M = Integer.parseInt(st.nextToken());   //바이러스 개수
        map = new int[N][N];
        visited = new boolean[N][N];
        allVirus = new ArrayList<>();
        selVirus = new Node[M];
        list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    allVirus.add(new Node(i, j));
                }
            }
        }

        combination(0, 0);
        System.out.println(Arrays.toString(list.get(2)));
    }

    static class Node {
        int r;
        int c;

        public Node(int r, int c) {
            super();
            this.r = r;
            this.c = c;
        }
    }

    //조합 구하기
    private static void combination(int cnt, int start) {
        if (cnt == M) {
            System.out.println(Arrays.toString(selVirus));
            list.add(selVirus);
            return;
        }

        for (int i = start; i < allVirus.size(); i++) {
            selVirus[cnt] = allVirus.get(i);
            combination(cnt + 1, i + 1);
        }
    }
}
