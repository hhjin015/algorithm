package Programmers;

import java.util.Arrays;

public class Solution_해시_level2_전화번호목록 {
    public static void main(String[] args) {
        System.out.println(Solution(new String[]{"119", "97674223", "1195524421"}));
        System.out.println(Solution(new String[]{"123", "456", "789"}));
        System.out.println(Solution(new String[]{"12", "123", "1235", "567", "88"}));
    }

    private static boolean Solution(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book);

        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i + 1].startsWith(phone_book[i])) {
                answer = false;
                break;
            }
        }
        return answer;
    }
}
