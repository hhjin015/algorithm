package Programmers;

import java.util.HashSet;

public class Solution_완전탐색_level2_소수찾기 {
    static int length;
    static char[] inputs;
    static boolean[] isSelected;
    static HashSet<Integer> hashSet;

    public static void main(String[] args) {
        String numbers = "011";
        length = numbers.length();

        inputs = new char[length];
        isSelected = new boolean[length];
        hashSet = new HashSet<>();

        for (int i = 0; i < length; i++) {
            inputs[i] = numbers.charAt(i);
        }

        subset(0, "");
        System.out.println(hashSet.size());
    }

    private static void subset(int cnt, String str) {
        int num;
        if (!str.equals("")) {
            num = Integer.parseInt(str);
            if (isPrimeNumber(num)) hashSet.add(num);
        }

        if (cnt == length) return;

        for (int i = 0; i < length; i++) {
            if (isSelected[i]) continue;
            isSelected[i] = true;
            subset(cnt + 1, str + inputs[i]);
            isSelected[i] = false;
        }
    }

    private static boolean isPrimeNumber(int num) {
        if (num == 0 || num == 1) return false;

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}
