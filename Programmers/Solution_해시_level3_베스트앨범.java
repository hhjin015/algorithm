package Programmers;

import java.util.*;

public class Solution_해시_level3_베스트앨범 {
    public static void main(String[] args) {
        int[] arr = Solution(new String[]{"classic", "pop", "classic", "classic", "pop"}, new int[]{500, 600, 150, 800, 2500});
        System.out.println(Arrays.toString(arr));
    }

    private static int[] Solution(String[] genres, int[] plays) {
        Map<String, Integer> map = new HashMap<>();

        // 장르별 재생 수 총합
        for (int i = 0; i < genres.length; i++) {
            map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
        }

        List<String> keys = new ArrayList<>();
        for (String key : map.keySet()) {
            keys.add(key);
        }

        Collections.sort(keys, (v1, v2) -> map.get(v2) - map.get(v1));  //내림차순 정렬

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);

            int max = 0;
            int firstIdx = -10;

            // 장르 내에서 재생 수 제일 높은 곡의 인덱스 찾기 first
            for (int j = 0; j < genres.length; j++) {
                if (key.equals(genres[j]) && max < plays[j]) {
                    max = plays[j];
                    firstIdx = j;
                }
            }

            int min = Integer.MAX_VALUE;
            int secondIdx = -10;

            //장르 내에서 재생 수 두 번째로 높은 곡의 인덱스 찾기 second (max와의 차이로 찾기)
            for (int j = 0; j < genres.length; j++) {
                int diff = Math.abs(max - plays[j]);
                if (key.equals(genres[j]) && min > diff && j != firstIdx) {
                    min = diff;
                    secondIdx = j;
                }
            }

            list.add(firstIdx);
            if (secondIdx >= 0) list.add(secondIdx);
        }

        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }

        return result;
    }
}
