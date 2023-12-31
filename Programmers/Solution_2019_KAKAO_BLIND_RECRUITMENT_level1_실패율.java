package Programmers;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution_2019_KAKAO_BLIND_RECRUITMENT_level1_실패율 {
    static class Info {
        int num;
        double rate;

        public Info(int num, double rate) {
            this.num = num;
            this.rate = rate;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(5, new int[]{2, 1, 2, 6, 2, 4, 3, 3})));
        System.out.println(Arrays.toString(solution(4, new int[]{4, 4, 4, 4, 4})));
    }

    private static int[] solution(int N, int[] stages) {
        PriorityQueue<Info> pq = new PriorityQueue<>((info1, info2) -> {
            // rate 기준 내림차순 정렬
            int rateComparison = Double.compare(info2.rate, info1.rate);

            // rate가 같으면 num 기준 오름차순 정렬
            if (rateComparison == 0) {
                return Integer.compare(info1.num, info2.num);
            }

            return rateComparison;
        });

        int all = stages.length;
        for (int i = 1; i <= N; i++) {
            int cnt = 0;

            for (int j = 0; j < stages.length; j++) {
                if (stages[j] == i) cnt++;
            }

            if (all == 0) pq.add(new Info(i, 0.0));
            else pq.add(new Info(i, (double) cnt / all));

            all -= cnt;
        }

        int[] answer = new int[N];
        int idx = 0;
        while (!pq.isEmpty()) {
            answer[idx++] = pq.poll().num;
        }

        return answer;
    }
}
