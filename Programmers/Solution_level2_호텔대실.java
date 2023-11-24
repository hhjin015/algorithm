package Programmers;

import java.util.PriorityQueue;

public class Solution_level2_호텔대실 {
    static class Info implements Comparable<Info> {
        int start, end;

        public Info(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Info o) {
            return this.start - o.start;
        }
    }

    public static void main(String[] args) {
        System.out.println(solution(new String[][]{{"15:00", "17:00"}, {"16:40", "18:20"}, {"14:20", "15:20"}, {"14:10", "19:20"}, {"18:20", "21:20"}}));
        System.out.println(solution(new String[][]{{"09:10", "10:10"}, {"10:20", "12:20"}}));
        System.out.println(solution(new String[][]{{"10:20", "12:30"}, {"10:20", "12:30"}, {"10:20", "12:30"}}));
    }

    private static int solution(String[][] book_time) {
        PriorityQueue<Info> books = new PriorityQueue<>();
        PriorityQueue<Info> rooms = new PriorityQueue<>();

        for (int i = 0; i < book_time.length; i++) {
            String start = book_time[i][0];
            String end = book_time[i][1];

            books.offer(new Info(hourToMinute(start), hourToMinute(end)));
        }

        int answer = 0;
        while (!books.isEmpty()) {
            Info now = books.poll();

            if (rooms.isEmpty()) {
                rooms.offer(new Info(now.start, now.end + 10));
                ++answer;
            } else {
                boolean flag = false;

                for (Info i : rooms) {
                    if (i.end <= now.start) {
                        i.end = now.end + 10;
                        flag = true;
                        break;
                    }
                }

                if (!flag) {
                    ++answer;
                    rooms.offer(new Info(now.start, now.end + 10));
                }
            }
        }

        return answer;
    }

    private static int hourToMinute(String time) {
        String[] strs = time.split(":");

        return Integer.parseInt(strs[0]) * 60 + Integer.parseInt(strs[1]);
    }
}
