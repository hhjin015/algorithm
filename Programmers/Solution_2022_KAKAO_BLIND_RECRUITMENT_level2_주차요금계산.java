package Programmers;

import java.util.*;

public class Solution_2022_KAKAO_BLIND_RECRUITMENT_level2_주차요금계산 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(Solution(new int[]{180, 5000, 10, 600}, new String[]{
                "05:34 5961 IN",
                "06:00 0000 IN",
                "06:34 0000 OUT",
                "07:59 5961 OUT",
                "07:59 0148 IN",
                "18:59 0000 IN",
                "19:09 0148 OUT",
                "22:59 5961 IN",
                "23:00 5961 OUT"})));
        System.out.println(Arrays.toString(Solution(new int[]{120, 0, 60, 591}, new String[]{
                "16:00 3961 IN",
                "16:00 0202 IN",
                "18:00 3961 OUT",
                "18:00 0202 OUT",
                "23:58 3961 IN"})));
        System.out.println(Arrays.toString(Solution(new int[]{1, 461, 1, 10}, new String[]{"00:00 1234 IN"})));
    }

    private static int[] Solution(int[] fees, String[] records) {
        Map<String, List<Integer>> map = new TreeMap<>();

        for (String s : records) {
            String[] str = s.split(" ");

            if (!map.containsKey(str[1])) {
                List<Integer> list = new ArrayList<>();
                list.add(changeToMinute(str[0]));

                map.put(str[1], list);
            } else {
                map.get(str[1]).add(changeToMinute(str[0]));
            }
        }

        int[] answer = new int[map.size()];
        int idx = -1;
        for (String s : map.keySet()) {
            if (map.get(s).size() % 2 == 1) map.get(s).add(1439);
            answer[++idx] = calculateFee(fees, map.get(s));
        }

        return answer;
    }

    private static int changeToMinute(String str) {
        String[] strings = str.split(":");
        return (Integer.parseInt(strings[0]) * 60) + Integer.parseInt(strings[1]);
    }

    private static int calculateFee(int[] fees, List<Integer> list) {
        int total = -fees[0];
        for (int i = 0; i < list.size(); i += 2) {
            total += list.get(i + 1) - list.get(i);
        }

        int fee = fees[1];
        if (total > 0) {
            fee += (total / fees[2]) * fees[3];
            if (total % fees[2] > 0) fee += fees[3];
        }

        return fee;
    }
}
