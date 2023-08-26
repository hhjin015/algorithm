package Programmers;

import java.util.HashSet;

public class Solution_SummerWinterCoding_level2_방문길이 {
    public static void main(String[] args) {
        System.out.println(Solution("ULURRDLLU"));
        System.out.println(Solution("LULLLLLLU"));
    }

    private static int Solution(String dirs) {
        HashSet<String> hs = new HashSet<>();

        int nowX = 0;
        int nowY = 0;

        for (int i = 0; i < dirs.length(); i++) {
            int nextX = nowX;
            int nextY = nowY;
            String str = "";

            switch (dirs.charAt(i)) {
                case 'U':
                    nextY++;
                    str += nowX;
                    str += nowY;
                    str += nextX;
                    str += nextY;
                    break;

                case 'D':
                    nextY--;
                    str += nextX;
                    str += nextY;
                    str += nowX;
                    str += nowY;
                    break;

                case 'R':
                    nextX++;
                    str += nowX;
                    str += nowY;
                    str += nextX;
                    str += nextY;
                    break;

                case 'L':
                    nextX--;
                    str += nextX;
                    str += nextY;
                    str += nowX;
                    str += nowY;
                    break;
            }

            if (nextX < -5 || nextY < -5 || nextX > 5 || nextY > 5) continue;

            hs.add(str);
            nowX = nextX;
            nowY = nextY;
        }
        return hs.size();
    }
}
