import java.util.HashMap;

public class Interpreter {

    public final HashMap<String, String> env;
    
    public Interpreter(HashMap<String, String> env) {
        this.env = env;
    }

    public static int operation(String op, String left, String right) {
        int result = 0;
        Token token;

        Token leftToken = new Token(Validator.getTokenType(left), left);
        
        Token rightToken = new Token(Validator.getTokenType(right), right);
        
        int leftInt = 0;
        if (Validator.getTokenType(left) == TokenType.NUM) {
            leftInt = Integer.parseInt(left);
        } else {
            if(Validator.hashContains(Task3.interpreter.env, left)) {
                String leftAux = Task3.interpreter.env.get(left);
                leftInt = Integer.parseInt(leftAux);
            } else {
                throw new Error(left + " cannot be resolved \n");
            }
        }

        int rightInt = 0;
        if (Validator.getTokenType(right) == TokenType.NUM) {
            rightInt = Integer.parseInt(right);
        } else {
            if(Validator.hashContains(Task3.interpreter.env, right)) {
                String rightAux = Task3.interpreter.env.get(right);
                rightInt = Integer.parseInt(rightAux);
            } else {
                throw new Error(right + " cannot be resolved \n");
            }
        }
        
        System.out.println(leftToken);
        System.out.println(rightToken);
        
        switch (op) {
        case "+":
            token = new Token(TokenType.PLUS, op);
            result = leftInt + rightInt;
            break;
        case "-":
            token = new Token(TokenType.MINUS, op);
            result = leftInt - rightInt;
            break;
        case "*":
            token = new Token(TokenType.STAR, op);
            result = leftInt * rightInt;
            break;
        case "/":
            token = new Token(TokenType.SLASH, op);
            result = leftInt / rightInt;
            break;
        default:
            token = new Token(TokenType.EOF, op);
            break;
        }

        System.out.println(token);

        return result;
    }
}
