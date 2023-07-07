package Programmers;

public class Solution_깊이너비우선탐색_level3_단어변환 {
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        System.out.println(Solution("hit", "cog", new String[]{"hot", "dot", "dog", "lot", "log", "cog"}));
        System.out.println(Solution("hit", "cog", new String[] {"hot", "dot", "dog", "lot", "log"}));
    }

    private static int Solution(String begin, String target, String[] words) {
        boolean[] visited = new boolean[words.length];

        dfs(begin, target, visited, words, 0);

        if (min == Integer.MAX_VALUE) return 0;
        return min;
    }

    private static void dfs(String now, String target, boolean[] visited, String[] words, int depth) {
        if (now.equals(target)) {
            min = Math.min(min, depth);
            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (!visited[i] && compare(now, words[i])) {
                visited[i] = true;
                dfs(words[i], target, visited, words, depth + 1);
                visited[i] = false;
            }
        }
    }

    private static boolean compare(String a, String b) {
        int cnt = 0;
        char[] arr_a = a.toCharArray();
        char[] arr_b = b.toCharArray();

        for (int i = 0; i < a.length(); i++) {
            if (arr_a[i] != arr_b[i]) ++cnt;
        }

        return cnt == 1;
    }
}
