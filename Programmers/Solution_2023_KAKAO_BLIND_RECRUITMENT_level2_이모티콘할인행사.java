package Programmers;

import java.util.Arrays;

public class Solution_2023_KAKAO_BLIND_RECRUITMENT_level2_이모티콘할인행사 {
    static int[] percentages = {10, 20, 30, 40};
    static int[] result = new int[2];

    static class User {
        int pay;
        int standard;
        int percentage;
        boolean isSub;

        public User(int pay, int standard, int percentage, boolean isSub) {
            this.pay = pay;
            this.standard = standard;
            this.percentage = percentage;
            this.isSub = isSub;
        }

        public void check(int price) {
            if (pay + price >= this.standard) {
                this.isSub = true;
                this.pay = 0;
            } else {
                this.isSub = false;
                this.pay += price;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(Solution(new int[][]{{40, 10000}, {25, 10000}}, new int[]{7000, 9000})));
        System.out.println(Arrays.toString(Solution(new int[][]{{40, 2900}, {23, 10000}, {11, 5200}, {5, 5900}, {40, 3100}, {27, 9200}, {32, 6900}}, new int[]{1300, 1500, 1600, 4900})));
    }

    private static int[] Solution(int[][] users, int[] emoticons) {
        User[] userInfos = new User[users.length];
        for (int i = 0; i < users.length; i++) {
            userInfos[i] = new User(0, users[i][1], users[i][0], false);
        }

        for (int i = 0; i < 4; i++) {
            User[] newUsers = copy(userInfos);
            recur(0, percentages[i], newUsers, emoticons);
        }

        return result;
    }

    private static void recur(int emoNum, int p, User[] users, int[] emoticons) {
        changeInfos(emoNum, p, users, emoticons);

        if (emoNum == emoticons.length - 1) {
            compare(users);
            return;
        }

        for (int i = 0; i < 4; i++) {
            User[] copyUser = copy(users);
            recur(emoNum + 1, percentages[i], copyUser, emoticons);
        }
    }

    private static void changeInfos(int emoNum, int p, User[] users, int[] emoticons) {
        int price = (int) (emoticons[emoNum] * (100 - p) * 0.01);

        for (int i = 0; i < users.length; i++) {
            User u = users[i];

            if (u.isSub) continue;
            if (u.percentage <= p) u.check(price);
        }
    }

    private static User[] copy(User[] original) {
        User[] newUsers = new User[original.length];

        for (int i = 0; i < original.length; i++) {
            User u = original[i];
            newUsers[i] = new User(u.pay, u.standard, u.percentage, u.isSub);
        }

        return newUsers;
    }

    private static void compare(User[] users) {
        int subCnt = 0;
        int totalPrice = 0;

        for (int i = 0; i < users.length; i++) {
            User u = users[i];

            if (u.isSub) subCnt++;
            else totalPrice += u.pay;
        }

        if (result[0] < subCnt) {
            result[0] = subCnt;
            result[1] = totalPrice;
        }

        if (result[0] == subCnt && result[1] < totalPrice) {
            result[1] = totalPrice;
        }
    }
}
