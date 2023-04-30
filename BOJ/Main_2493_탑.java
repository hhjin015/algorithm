package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_2493_탑 {
    static class Top {
        int height, idx;

        public Top(int height, int idx) {
            this.height = height;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Stack<Top> stack = new Stack<>();

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            int n = Integer.parseInt(st.nextToken());

            while (!stack.isEmpty()) {
                if (stack.peek().height > n) {
                    sb.append(stack.peek().idx).append(" ");
                    break;
                }
                stack.pop();
            }
            if (stack.isEmpty()) sb.append(0).append(" ");

            stack.push(new Top(n, i));
        }
        System.out.println(sb);
    }

}
