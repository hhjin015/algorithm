package Programmers;

import java.util.Stack;

public class Solution_PCCP기출문제_level1_1번 {
    public static void main(String[] args) {
        System.out.println(solution(new int[]{5, 1, 5}, 30, new int[][]{{2, 10}, {9, 15}, {10, 5}, {11, 5}}));
        System.out.println(solution(new int[]{3, 2, 7}, 20, new int[][]{{1, 15}, {5, 16}, {8, 6}}));
    }

    private static int solution(int[] bandage, int health, int[][] attacks) {
        int size = attacks.length;
        int lastAttack = attacks[size - 1][0];
        int sequence = 0;
        int currentHealth = health;
        Stack<int[]> stack = new Stack<>();

        for (int i = size - 1; i >= 0; i--) {
            stack.push(attacks[i]);
        }

        for (int i = 1; i <= lastAttack; i++) {
            if (stack.peek()[0] == i) {
                int[] attack = stack.pop();
                currentHealth -= attack[1];
                sequence = 0;

                if (currentHealth <= 0) return -1;
                continue;
            }
            currentHealth += bandage[1];

            if (++sequence == bandage[0]) {
                sequence = 0;
                currentHealth += bandage[2];
            }

            if (currentHealth > health) currentHealth = health;
        }

        return currentHealth;
    }
}
