package Programmers;

public class Solution_탐욕법_level2_조이스틱 {
    public static void main(String[] args) {
        System.out.println(Solution("JEROEN"));
        System.out.println(Solution("JAN"));
    }

    private static int Solution(String name) {
        int answer = 0;
        int length = name.length();
        int min = length - 1;

        for (int i = 0; i < length; i++) {
            char c = name.charAt(i);
            int mov = Math.min(c - 'A', 'Z' - c + 1);

            answer += mov;

            int nextIndex = i + 1;
            while (nextIndex < length && name.charAt(nextIndex) == 'A') {
                nextIndex++;
            }

            min = Math.min(min, (i * 2) + length - nextIndex);
        }

        answer += min;

        return answer;
    }
}
