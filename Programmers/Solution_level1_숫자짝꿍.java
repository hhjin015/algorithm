package Programmers;

public class Solution_level1_숫자짝꿍 {
    public static void main(String[] args) {
        System.out.println(solution("100", "2345"));
        System.out.println(solution("100", "203045"));
        System.out.println(solution("100", "123450"));
        System.out.println(solution("12321", "42531"));
        System.out.println(solution("5525", "1255"));
    }

    private static String solution(String X, String Y) {
        String[] splitX = X.split("");
        String[] splitY = Y.split("");

        int[] x = new int[10];
        int[] y = new int[10];

        for (String s : splitX) {
            x[Integer.parseInt(s)]++;
        }

        for (String s : splitY) {
            y[Integer.parseInt(s)]++;
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 9; i >= 0; i--) {
            int min = Math.min(x[i], y[i]);

            for (int j = 0; j < min; j++) {
                if (sb.length() == 0 && i == 0) return "0";
                sb.append(i);
            }
        }

        if (sb.length() == 0) return "-1";

        return sb.toString();
    }
}
