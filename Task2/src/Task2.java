import java.util.Scanner;
import java.util.Stack;

import java.io.File;
import java.io.FileNotFoundException;

public class Task2 {

    private static Stack<String> stack = new Stack<String>();

    public static void main(String[] args) {
        String path = ".../Calc1.stk";

        try {
            File file = new File(path);
            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {
                String data = fileReader.nextLine();
                if (isValidInput(data)) {
                    stack.push(data);
                } else {
                    System.out.printf("Error: Unexpected character: %s \n", data);
                    fileReader.close();
                }

                if (stack.size() == 3) {
                    String op = stack.pop();
                    int firstNumber = Integer.parseInt(stack.pop());
                    int secondNumber = Integer.parseInt(stack.pop());

                    operation(op, firstNumber, secondNumber);
                }
            }

            fileReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error when trying to read the file...");
            e.printStackTrace();
        }

        System.out.printf("Saida: %s \n", stack.pop());
    }

    public static boolean isValidInput(String str) {
        if ((stack.size() == 0 && isDigit(str)) || 
            (stack.size() % 2 == 0 && isOperator(str)) || 
            (stack.size() % 2 != 0 && isDigit(str))) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isDigit(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isOperator(String str) {
        if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {            
            return true;
        } else {
            return false;
        }
    }

    public static void operation(String op, int x, int y) {
        int result = 0;
        Token token;

        Token intX = new Token(TokenType.NUM, Integer.toString(x));
        System.out.println(intX);

        Token intY = new Token(TokenType.NUM, Integer.toString(y));
        System.out.println(intY);

        switch (op) {
        case "+":
            token = new Token(TokenType.PLUS, op);
            result = x + y;
            break;
        case "-":
            token = new Token(TokenType.MINUS, op);
            result = x - y;
            break;
        case "*":
            token = new Token(TokenType.STAR, op);
            result = x * y;
            break;
        case "/":
            token = new Token(TokenType.SLASH, op);
            result = x / y;
            break;
        default:
            token = new Token(TokenType.EOF, op);
            break;
        }

        System.out.println(token);

        stack.push(Integer.toString(result));
    }
}
