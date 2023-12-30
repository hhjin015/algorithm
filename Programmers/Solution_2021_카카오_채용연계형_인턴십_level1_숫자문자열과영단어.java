package Programmers;

public class Solution_2021_카카오_채용연계형_인턴십_level1_숫자문자열과영단어 {
    public static void main(String[] args) {
        System.out.println(solution("one4seveneight"));
        System.out.println(solution("23four5six7"));
        System.out.println(solution("2three45sixseven"));
        System.out.println(solution("123"));
    }

    private static int solution(String s) {
        String[] numbers = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

        for (int i = 0; i < 10; i++) {
            s = s.replace(numbers[i], Integer.toString(i));
        }

        return Integer.parseInt(s);
    }
}
