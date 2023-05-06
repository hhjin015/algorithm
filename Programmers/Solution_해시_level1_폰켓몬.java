package Programmers;

import java.util.HashSet;

public class Solution_해시_level1_폰켓몬 {
    public static void main(String[] args) {
        System.out.println(Solution(new int[]{3, 1, 2, 3}));
        System.out.println(Solution(new int[]{3, 3, 3, 2, 2, 4}));
        System.out.println(Solution(new int[]{3, 3, 3, 2, 2, 2}));
    }

    private static int Solution(int[] nums) {
        int answer = 0;
        int n = nums.length / 2;

        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            hashSet.add(nums[i]);
        }

        if (n < hashSet.size()) answer = n;
        else answer = hashSet.size();

        return answer;
    }
}
