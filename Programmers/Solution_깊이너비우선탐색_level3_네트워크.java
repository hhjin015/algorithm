package Programmers;

public class Solution_깊이너비우선탐색_level3_네트워크 {
    public static void main(String[] args) {
        System.out.println(Solution(3, new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
        System.out.println(Solution(3, new int[][]{{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}));
    }

    private static int Solution(int n, int[][] computers) {
        boolean[] visited = new boolean[n];
        int answer = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, n, computers, visited);
                ++answer;
            }
        }
        return answer;
    }

    private static void dfs(int num, int n, int[][] computers, boolean[] visited) {
        visited[num] = true;

        for (int i = 0; i < n; i++) {
            if (!visited[i] && computers[num][i] == 1) {
                dfs(i, n, computers, visited);
            }
        }
    }
}
