package BOJ;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main_1259_팰린드롬수 {
    public static void main(String[] args) throws Exception{
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();

        while(true) {
            String s = br.readLine();
            if(s.equals("0")) break;

            StringBuffer stringBuffer = new StringBuffer(s);
            if (stringBuffer.reverse().toString().equals(s)) {
                sb.append("yes"+ "\n");
            }else sb.append("no" + "\n");
        }

        System.out.println(sb);
    }
}
