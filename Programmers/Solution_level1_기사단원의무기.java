package Programmers;

public class Solution_level1_기사단원의무기 {
    public static void main(String[] args) {
        System.out.println(solution(5, 3, 2));
        System.out.println(solution(10, 3, 2));
    }

    private static int solution(int number, int limit, int power) {
        int answer = 0;

        for (int i = 1; i <= number; i++) {
            int cnt = getDivisor(i);

            if (cnt > limit) answer += power;
            else answer += cnt;
        }

        return answer;
    }

    private static int getDivisor(int num) {
        int sqrt = (int) Math.sqrt(num);
        int cnt = 0;

        for (int i = 1; i <= sqrt; i++) {
            if (i * i == num) cnt++;
            else if (num % i == 0) cnt += 2;
        }

        return cnt;
    }
}
