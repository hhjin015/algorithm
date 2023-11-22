package Programmers;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

public class Solution_level2_과제진행하기 {
    static class Task implements Comparable<Task> {
        String name;
        int start;
        int playtime;

        public Task(String name, int start, int playtime) {
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }

        @Override
        public int compareTo(Task o) {
            return this.start - o.start;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(Solution(new String[][]{{"korean", "11:40", "30"}, {"english", "12:10", "20"}, {"math", "12:30", "40"}})));
        System.out.println(Arrays.toString(Solution(new String[][]{{"science", "12:40", "50"}, {"music", "12:20", "40"}, {"history", "14:00", "30"}, {"computer", "12:30", "100"}})));
        System.out.println(Arrays.toString(Solution(new String[][]{{"aaa", "12:00", "20"}, {"bbb", "12:10", "30"}, {"ccc", "12:40", "10"}})));
    }

    private static String[] Solution(String[][] plans) {
        String[] answer = new String[plans.length];
        PriorityQueue<Task> pq = new PriorityQueue<>();
        Stack<Task> stop = new Stack<>();

        for (int i = 0; i < plans.length; i++) {
            String name = plans[i][0];
            String[] str = plans[i][1].split(":");
            int minutes = Integer.parseInt(str[0]) * 60 + Integer.parseInt(str[1]);

            pq.offer(new Task(name, minutes, Integer.parseInt(plans[i][2])));
        }

        int idx = 0;
        while (!pq.isEmpty()) {
            Task nowTask = pq.poll();

            if (!pq.isEmpty()) {
                int diff = pq.peek().start - nowTask.start;

                if (diff == nowTask.playtime) answer[idx++] = nowTask.name;
                else if (diff < nowTask.playtime) {
                    nowTask.playtime -= diff;
                    stop.add(nowTask);
                } else {
                    answer[idx++] = nowTask.name;
                    int rest = diff - nowTask.playtime;

                    while (!stop.isEmpty()) {
                        if (rest == 0) break;

                        Task p = stop.pop();
                        if (p.playtime <= rest) {
                            answer[idx++] = p.name;
                            rest -= p.playtime;
                        } else {
                            p.playtime -= rest;
                            stop.add(p);
                            rest = 0;
                        }
                    }
                }
            } else answer[idx++] = nowTask.name;
        }

        while (!stop.isEmpty()) {
            answer[idx++] = stop.pop().name;
        }

        return answer;
    }
}
