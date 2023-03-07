package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_14621_나만안되는연애 {
    static int N, M;
    static int[] parents;
    static char[] university;
    static Edge[] edgeList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());    //학교 수
        M = Integer.parseInt(st.nextToken());    //도로 수

        university = new char[N + 1];
        edgeList = new Edge[M];    //간선 리스트

        //성별 입력
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            university[i] = st.nextToken().charAt(0);
        }

        //간선 리스트 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            edgeList[i] = new Edge(u, v, d);
        }

        Arrays.sort(edgeList);

        makeSet();

        int cnt = 0;
        int ans = 0;

        for (Edge edge : edgeList) {
            if (union(edge.from, edge.to)) {
                cnt++;
                ans += edge.distance;
            }
        }

        if (cnt != N - 1) System.out.println(-1);
        else System.out.println(ans);
    }

    static class Edge implements Comparable<Edge> {
        int from, to, distance;

        public Edge(int from, int to, int distance) {
            this.from = from;
            this.to = to;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            return this.distance - o.distance;
        }

        @Override
        public String toString() {
            return "Edge [from=" + from + ", to=" + to + ", distance=" + distance + "]";
        }
    }

    // 단위집합 생성
    public static void makeSet() {
        parents = new int[N + 1];
        //자신의 부모노드를 자신의 값으로 세팅
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
    }

    //a의 집합 찾기 : a의 대표자 찾기
    public static int findSet(int a) {
        if (a == parents[a]) return a;
        return parents[a] = findSet(parents[a]);
    }

    // a, b 두 집합 합치기
    public static boolean union(int a, int b) {
        int aRoot = findSet(a);
        int bRoot = findSet(b);
        if (aRoot == bRoot || university[a] == university[b]) return false;

        parents[bRoot] = aRoot;
        return true;
    }
}
