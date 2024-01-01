package Programmers;

public class Solution_그리디_level2_큰수만들기 {
    public static void main(String[] args) {
        System.out.println(solution("1924", 2));
        System.out.println(solution("1231234", 3));
        System.out.println(solution("4177252841", 4));
        System.out.println(solution("720378", 2));
        System.out.println(solution("1234567890123456789012345678901234567890", 1));
    }

    private static String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        int endIdx = number.length() - (number.length() - k - 1);
        int startIdx = 0;

        while (endIdx <= number.length()) {
            int max = -1;

            for (int i = startIdx; i < endIdx; i++) {
                int comp = number.charAt(i) - '0';

                if (max < comp) {
                    startIdx = i + 1;
                    max = comp;
                }
            }

            sb.append(max);
            endIdx++;
        }

        return sb.toString();
    }
}
