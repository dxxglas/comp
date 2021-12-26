import java.util.HashMap;

public class Validator {

    public static boolean isDigit(String str) {
        return str.matches("(\\d)+");
    }

    public static boolean isOperator(String str) {
        return str.matches( "(\\+|-|\\*|/)" );
    }

    public static boolean isVariable(String str) {
        return str.matches("(\\w)+");
    }

    public static TokenType getTokenType(String token) {
		TokenType tokenType = null;

		if(isDigit(token)) {
			tokenType = TokenType.NUM;
		}
		else if(isVariable(token)) {
			tokenType =  TokenType.ID;
		}
		
		return tokenType;
	}

    public static boolean hashContains(HashMap<String, String> hash, String token) {
        return hash.containsKey(token);
    }
}
