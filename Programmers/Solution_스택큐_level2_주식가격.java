package Programmers;

import java.util.Arrays;
import java.util.Stack;

public class Solution_스택큐_level2_주식가격 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(Solution(new int[]{1, 2, 3, 2, 3})));
    }

    private static int[] Solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Integer> stack = new Stack();

        for (int i = 0; i < prices.length; i++) {
            while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                answer[stack.peek()] = i - stack.peek();
                stack.pop();
            }

            stack.push(i);
        }

        while (!stack.isEmpty()) {
            answer[stack.peek()] = prices.length - stack.peek() - 1;
            stack.pop();
        }

        return answer;
    }
}
