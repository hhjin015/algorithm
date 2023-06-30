package Codility;

public class Solution_FrogJmp {
    public static void main(String[] args) {
        System.out.println(Solution(5, 105, 3));
    }

    private static int Solution(int X, int Y, int D) {
        if (X == Y) return 0;

        Y -= X;
        X = 0;

        int quot = Y / D;
        if (quot >= 1) {
            if (X + (D * quot) >= Y) return quot;
            return quot + 1;
        } else return 1;
    }
}
