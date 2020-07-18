import java.util.Scanner;
import java.util.Stack;

class Main {
    public static void main(String[] args) {
        // put your code here
        boolean ans = false;
        Scanner s = new Scanner(System.in);
        Stack<String> stack = new Stack<>();
        String[] strings = s.nextLine().split("");
        for (String str : strings){
            if (str.matches("[),},\\]]")){
                if(stack.isEmpty()) {
                    stack.push(str);
                } else {
                    if (str.equals("}") && stack.peek().equals("{")) {
                        stack.pop();
                    } else if (str.equals("]") && stack.peek().equals("[")) {
                        stack.pop();
                    } else if (str.equals(")") && stack.peek().equals("(")){
                        stack.pop();
                    }
                }
            } else {
                stack.push(str);
            }
        }
        System.out.println(stack.isEmpty());
    }
}