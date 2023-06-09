import java.util.Scanner;
import java.util.Stack;

public class PostfixEvaluation {
    public static void main(String[] args) throws NumberFormatException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter infix expression: ");
        String infixExpression = scanner.nextLine();
        scanner.close();

        String postfixExpression = infixToPostfix(infixExpression);
        int result = evaluatePostfix(postfixExpression);

        System.out.println("The infix expression is: " + getFormattedInfixExpression(infixExpression));
        System.out.println("The postfix expression is: " + postfixExpression);
        System.out.println("The result is: " + result);
    }

    private static String getFormattedInfixExpression(String infixExpression) {
        infixExpression = infixExpression.replace("ob", "(");
        infixExpression = infixExpression.replace("cb", ")");
        infixExpression = infixExpression.replace("add", "+");
        infixExpression = infixExpression.replace("sub", "-");
        infixExpression = infixExpression.replace("mul", "*");
        infixExpression = infixExpression.replace("div", "/");
        infixExpression = infixExpression.replace("mod", "%");

        return infixExpression;
    }

    public static int evaluatePostfix(String postfix) {
        Stack<Integer> valueStack = new Stack<>();
        String[] tokens = postfix.split(" ");
        for (String token : tokens) {
            if (isNumeric(token)) {
                valueStack.push(Integer.parseInt(token));
            } else if (token.equals("+") || token.equals("-") || token.equals("*")
                    || token.equals("/") || token.equals("%")) {
                if (valueStack.isEmpty()) {
                    // Handle the case where there are not enough operands on the stack
                    throw new IllegalArgumentException("Invalid postfix expression: Not enough operands");
                }
                int operandTwo = valueStack.pop();
                if (valueStack.isEmpty()) {
                    // Handle the case where there are not enough operands on the stack
                    throw new IllegalArgumentException("Invalid postfix expression: Not enough operands");
                }
                int operandOne = valueStack.pop();
                int result = compute(operandOne, operandTwo, token);
                valueStack.push(result);
            }
        }

        if (valueStack.isEmpty()) {
            // Handle the case where the stack is empty after evaluating the expression
            throw new IllegalArgumentException("Invalid postfix expression: Stack is empty");
        }

        return valueStack.peek();
    }

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private static int compute(int operandOne, int operandTwo, String operator) {
        int result;

        switch (operator) {
            case "+":
                result = operandOne + operandTwo;
                break;
            case "-":
                result = operandOne - operandTwo;
                break;
            case "*":
                result = operandOne * operandTwo;
                break;
            case "/":
                result = operandOne / operandTwo;
                break;
            case "%":
                result = operandOne % operandTwo;
                break;
            default:    // Unexpected character
                result = 0;
                break;
        }

        return result;
    }

    private static String infixToPostfix(String infixExpression) {
        StringBuilder postfixExpression = new StringBuilder();
        Stack<Character> operatorStack = new Stack<>();

        for (int i = 0; i < infixExpression.length(); i++) {
            char ch = infixExpression.charAt(i);

            if (ch == ' ') {
                continue; // Skip whitespace
            } else if (Character.isDigit(ch)) {
                postfixExpression.append(ch);
            } else if (ch == '(') {
                operatorStack.push(ch);
            } else if (ch == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    postfixExpression.append(" ").append(operatorStack.pop());
                }
                operatorStack.pop();
            } else {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(' && precedence(operatorStack.peek()) >= precedence(ch)) {
                    postfixExpression.append(" ").append(operatorStack.pop());
                }
                operatorStack.push(ch);
            }
        }

        while (!operatorStack.isEmpty()) {
            postfixExpression.append(" ").append(operatorStack.pop());
        }

        return postfixExpression.toString();
    }

    private static int precedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case '%':
                return 2;
        }
        return -1; // Unknown operator
    }
}


