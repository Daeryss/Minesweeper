import java.util.Scanner;

class ConcatenateStringsProblem {

    public static String concatenateStringsWithoutDigits(String[] strings) {
        // write your code with StringBuilder here
        StringBuilder str = new StringBuilder();
        for (String st : strings){
            String[] word = st.split("");
            for (String s :word) {
                if (!s.matches("[0-9]")) {
                    str.append(s);
                }
            }
        }
        return str.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] strings = scanner.nextLine().split(" +");
        String result = concatenateStringsWithoutDigits(strings);
        System.out.println(result);
    }
}