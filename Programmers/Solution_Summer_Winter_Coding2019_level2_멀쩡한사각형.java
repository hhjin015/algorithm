package Programmers;

public class Solution_Summer_Winter_Coding2019_level2_멀쩡한사각형 {
    public static void main(String[] args) {
        System.out.println(Solution(8, 12));
    }

    private static long Solution(int w, int h) {
        int gcd = getGCD(w, h);

        int width = w / gcd;
        int height = h / gcd;

        return ((long) w * h) - (long) (width + height - 1) * gcd;
    }

    private static int getGCD(int n1, int n2) {
        if (n1 % n2 == 0) return n2;

        return getGCD(n2, n1 % n2);
    }
}
