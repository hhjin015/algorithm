package Programmers;

import java.util.HashMap;

public class Solution_해시_level2_위장 {
    public static void main(String[] args) {
        String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};
        System.out.println(Solution(clothes));
    }

    private static int Solution(String[][] clothes) {
        int answer = 1;
        HashMap<String, Integer> hashMap = new HashMap<>();

        for (int i = 0; i < clothes.length; i++) {
            hashMap.put(clothes[i][1], hashMap.getOrDefault(clothes[i][1], 0) + 1);
        }

        for (String key : hashMap.keySet()) {
            answer *= hashMap.get(key) + 1;
        }
        answer--;
        return answer;
    }
}
