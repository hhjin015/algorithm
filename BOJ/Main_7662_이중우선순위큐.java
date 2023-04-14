package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main_7662_이중우선순위큐 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            int K = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> tm = new TreeMap<>();

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                String c = st.nextToken();
                int n = Integer.parseInt(st.nextToken());

                if (c.equals("I")) tm.put(n, tm.getOrDefault(n, 0) + 1);
                else {
                    if (tm.isEmpty()) continue;
                    if (n == -1) {
                        int min = tm.firstKey();
                        if (tm.get(min) == 1) {
                            tm.remove(min);
                        } else {
                            tm.put(min, tm.get(min) - 1);
                        }
                    } else {
                        int max = tm.lastKey();
                        if (tm.get(max) == 1) {
                            tm.remove(max);
                        } else {
                            tm.put(max, tm.get(max) - 1);
                        }
                    }
                }
            }
            if (tm.isEmpty()) sb.append("EMPTY").append("\n");
            else {
                sb.append(tm.lastKey()).append(" ");
                sb.append(tm.firstKey()).append("\n");
            }
        }
        System.out.println(sb);
    }
}
