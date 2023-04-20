package Programmers;

public class Solution_깊이너비우선탐색_level2_타겟넘버 {
    static int ans;

    public static void main(String[] args) {
        System.out.println(Solution(new int[]{1, 1, 1, 1, 1}, 3));
        System.out.println(Solution(new int[]{4, 1, 2, 1}, 4));
    }

    private static int Solution(int[] numbers, int target) {
        ans = 0;
        dfs(0, 0, numbers, target);
        return ans;
    }

    private static void dfs(int num, int depth, int[] numbers, int target) {
        if (depth == numbers.length) {
            if (num == target) ans++;
            return;
        }

        dfs(num + numbers[depth], depth + 1, numbers, target);
        dfs(num - numbers[depth], depth + 1, numbers, target);
    }
}
