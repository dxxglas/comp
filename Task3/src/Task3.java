import java.util.Scanner;
import java.util.Stack;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.HashMap;

public class Task3 {

    private static Stack<String> stack = new Stack<String>();

    public static final Interpreter interpreter = new Interpreter(new HashMap<String, String>());
    public static void main(String[] args) {
        String path = ".../Calc1.stk";

        interpreter.env.put("y", "10");

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
                    String left = stack.pop();
                    String right = stack.pop();

                    int resultOp = Interpreter.operation(op, left, right);
                    stack.push(Integer.toString(resultOp));
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
        if ((stack.size() == 0 && (Validator.isDigit(str) || Validator.isVariable(str))) || 
            (stack.size() % 2 == 0 && Validator. isOperator(str)) || 
            (stack.size() % 2 != 0 && (Validator.isDigit(str) || Validator.isVariable(str)))) {
            return true;
        } else {
            return false;
        }
    }
}
