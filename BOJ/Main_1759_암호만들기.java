package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main_1759_암호만들기 {
    static int L, C;
    static String[] inputs, selected;
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        inputs = new String[C];
        selected = new String[L];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            inputs[i] = st.nextToken();
        }
        Arrays.sort(inputs);
        combination(0, 0);
        System.out.println(sb);
    }

    private static void combination(int cnt, int start) {
        if (cnt == L) {
            if (checkCondition()) {
                sb.append(Arrays.stream(selected).collect(Collectors.joining())).append("\n");
            }
            return;
        }

        for (int i = start; i < C; i++) {
            selected[cnt] = inputs[i];
            combination(cnt + 1, i + 1);
        }
    }

    private static boolean checkCondition() {
        String str = Arrays.toString(selected);
        String[] strings = {"a", "e", "i", "o", "u"};
        int n = 0;
        boolean flag = false;
        for (int i = 0; i < 5; i++) {
            if (str.contains(strings[i])) {
                flag = true;
                n++;
            }
        }
        return flag && n <= L - 2;
    }
}
