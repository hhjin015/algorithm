package Programmers;

public class Solution_그리디_level2_마법의엘리베이터 {
    public static void main(String[] args) {
        System.out.println(solution(16));
        System.out.println(solution(2554));
    }

    private static int solution(int storey) {
        int answer = 0;

        while (storey > 0) {
            int num = storey % 10;
            storey /= 10;

            if (num < 5) {
                answer += num;
            } else if (num > 5) {
                storey++;
                answer += (10 - num);
            } else {
                if (storey % 10 >= 5) storey++;
                answer += 5;
            }
        }

        return answer;
    }
}
