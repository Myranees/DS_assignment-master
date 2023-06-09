
import java.util.*;

public class PostfixCalculator {
    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case '%':
                return 2;
            default:
                return -1;
        }
    }

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '%';
    }

    private static List<String> infixToPostfix(String infix) {
        List<String> postfix = new ArrayList<>();
        Deque<Character> stack = new ArrayDeque<>();

        StringBuilder operand = new StringBuilder();

        for (int i = 0; i < infix.length(); i++) {
            char ch = infix.charAt(i);

            if (Character.isDigit(ch)) {
                operand.append(ch);
            } else if (isOperator(ch)) {
                if (operand.length() > 0) {
                    postfix.add(operand.toString());
                    operand.setLength(0);
                }

                while (!stack.isEmpty() && stack.peek() != '(' && precedence(ch) <= precedence(stack.peek())) {
                    postfix.add(String.valueOf(stack.pop()));
                }

                stack.push(ch);
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                if (operand.length() > 0) {
                    postfix.add(operand.toString());
                    operand.setLength(0);
                }

                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.add(String.valueOf(stack.pop()));
                }

                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                }
            }
        }

        if (operand.length() > 0) {
            postfix.add(operand.toString());
        }

        while (!stack.isEmpty()) {
            postfix.add(String.valueOf(stack.pop()));
        }

        return postfix;
    }

    private static int evaluatePostfix(List<String> postfix) {
        Deque<Integer> stack = new ArrayDeque<>();

        for (String token : postfix) {
            if (Character.isDigit(token.charAt(0))) {
                stack.push(Integer.parseInt(token));
            } else {
                int operand2 = stack.pop();
                int operand1 = stack.pop();

                switch (token) {
                    case "+":
                        stack.push(operand1 + operand2);
                        break;
                    case "-":
                        stack.push(operand1 - operand2);
                        break;
                    case "*":
                        stack.push(operand1 * operand2);
                        break;
                    case "/":
                        stack.push(operand1 / operand2);
                        break;
                    case "%":
                        stack.push(operand1 % operand2);
                        break;
                }
            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter infix expression: ");
        String infix = scanner.nextLine().replace("ob", "(").replace("cb", ")").replace("add", "+").replace("sub", "-").replace("mul", "*").replace("div", "/").replace("mod", "%");

        List<String> postfix = infixToPostfix(infix);
        System.out.println("The infix expression is: " + infix);

        StringBuilder postfixString = new StringBuilder();
        for (String token : postfix) {
            postfixString.append(token).append(" ");
        }
        System.out.println("The postfix expression is: " + postfixString);

        int result = evaluatePostfix(postfix);
        System.out.println("The result is: " + result);
    }
}
