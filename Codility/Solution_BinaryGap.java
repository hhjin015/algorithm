package Codility;

public class Solution_BinaryGap {
    public static void main(String[] args) {
        System.out.println(Solution(66561));
    }

    private static int Solution(int N) {
        String binary = Integer.toBinaryString(N);

        int max = 0;
        for (int i = 0; i < binary.length(); i++) {
            if (binary.charAt(i) == '1') {
                int cnt = 0;
                for (int j = i + 1; j < binary.length(); j++) {
                    if (binary.charAt(j) == '1') {
                        max = Math.max(max, cnt);
                        i = j - 1;
                        break;
                    } else if (j == binary.length() - 1) {
                        return max;
                    }

                    cnt++;
                }
            }
        }

        return max;
    }
}
