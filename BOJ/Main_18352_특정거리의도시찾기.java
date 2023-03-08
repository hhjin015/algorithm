package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_18352_특정거리의도시찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());    //도시 개수
        int M = Integer.parseInt(st.nextToken());    //도로 개수
        int K = Integer.parseInt(st.nextToken());    //거리 정보
        int X = Integer.parseInt(st.nextToken());    //출발 도시의 번호

        ArrayList<Integer>[] list = new ArrayList[N + 1];

        for (int i = 0; i < list.length; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            list[from].add(to);        //단방향 그래프
        }

        Queue<Integer> q = new LinkedList<>();
        q.add(X);    //출발 도시 번호 큐에 넣기
        int[] distance = new int[N + 1];

        while (!q.isEmpty()) {
            int num = q.poll();

            for (int i = 0; i < list[num].size(); i++) {
                if (distance[list[num].get(i)] == 0) {
                    distance[list[num].get(i)] = distance[num] + 1;
                    q.add(list[num].get(i));
                }
            }
            System.out.println(Arrays.toString(distance));
        }

        boolean isOk = false;
        for (int i = 1; i < distance.length; i++) {
            if (distance[i] == K && i != X) {    //check[i]의 거리가 특정 거리와 같고 출발지가 아니라면 도시번호 출력
                isOk = true;
                System.out.println(i);
            }
        }
        if (!isOk) System.out.println(-1);
    }
}
