import java.util.Scanner;
import java.util.Stack;

class Main {
    public static void main(String[] args) {
        // put your code here
        Stack<Integer> stack = new Stack<>();
        Scanner s = new Scanner(System.in);
        int num =  s.nextInt();
        for (int i = 0; i < num; i++){
            stack.push(s.nextInt());
        }
        for (int i = 0; i < num; i++){
            System.out.println(stack.pop());

        }
    }
}