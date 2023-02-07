package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_1652_누울자리를찾아라 {
    static int N, width, height;
    static char rooms[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        rooms = new char[N][N];
        width = 0;
        height = 0;

        for(int i=0; i<N; i++) {
            String str = br.readLine();
            for(int j=0; j<N; j++) {
                rooms[i][j] = str.charAt(j);
            }
        }

        // 누울 수 있는 자리 찾기
        for(int i=0; i<N; i++) {
            int check_width = 0;
            int check_height = 0;
            for(int j=0; j<N; j++) {

                // 가로 체크
                if(rooms[i][j] == '.') check_width++;
                if(rooms[i][j] == 'X'|| (j == N-1)) {
                    if(check_width >= 2) {
                        width++;
                    }
                    check_width = 0;
                }

                // 세로 체크
                if(rooms[j][i] == '.') check_height++;
                if(rooms[j][i] == 'X'|| (j == N-1)) {
                    if(check_height >= 2) {
                        height++;
                    }
                    check_height = 0;
                }
            }

        }
            System.out.println(width + " " + height);

    }
}
