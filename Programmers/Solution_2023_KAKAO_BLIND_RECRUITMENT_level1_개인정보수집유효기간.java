package Programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Solution_2023_KAKAO_BLIND_RECRUITMENT_level1_개인정보수집유효기간 {
    static HashMap<String, Integer> arr_terms = new HashMap<>();
    static int[] arr_today;

    public static void main(String[] args) {
        int[] arr1 = Solution("2022.05.19", new String[]{"A 6", "B 12", "C 3" }, new String[]{"2021.05.02 A", "2021.07.01 B", "2022.02.19 C", "2022.02.20 C" });
        int[] arr2 = Solution("2020.01.01", new String[]{"Z 3", "D 5" }, new String[]{"2019.01.01 D", "2019.11.15 Z", "2019.08.02 D", "2019.07.01 D", "2018.12.28 Z" });

        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
    }

    static class Info {
        int year, month, day, idx;
        String type;

        public Info(int year, int month, int day, int idx, String type) {
            this.year = year;
            this.month = month;
            this.day = day;
            this.idx = idx;
            this.type = type;
        }
    }

    private static int[] Solution(String today, String[] terms, String[] privacies) {
        arr_today = new int[]{Integer.parseInt(today.split("[.]")[0]),
                Integer.parseInt(today.split("[.]")[1]),
                Integer.parseInt(today.split("[.]")[2])};

        for (String str : terms) {
            arr_terms.put(str.split(" ")[0], Integer.parseInt(str.split(" ")[1]));
        }

        Info[] infos = new Info[privacies.length];
        for (int i = 0; i < privacies.length; i++) {
            String[] str = privacies[i].split("[.]| ");
            infos[i] = new Info(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]), i, str[3]);
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < infos.length; i++) {
            int[] expiredDay = getExpiredDay(infos[i]);
            if (isExpired(expiredDay)) list.add(infos[i].idx + 1);
        }

        int[] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }

    private static int[] getExpiredDay(Info info) {
        int[] date = new int[3];
        int period = arr_terms.get(info.type);
        date[2] = info.day;

        if (info.month + period > 12) {
            int q = period / 12;
            int r = period % 12;

            date[0] = info.year + q;
            date[1] = info.month + r;

            if (date[1] > 12) {
                date[0]++;
                date[1] -= 12;
            }
        } else {
            date[0] = info.year;
            date[1] = info.month + period;
        }
        return date;
    }

    private static boolean isExpired(int[] date) {
        if (arr_today[0] < date[0]) return false;
        else if (arr_today[0] > date[0]) return true;

        if (arr_today[1] < date[1]) return false;
        else if (arr_today[1] > date[1]) return true;

        if (arr_today[2] >= date[2]) return true;
        return false;
    }
}
