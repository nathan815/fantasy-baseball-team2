import java.util.ArrayList;
import java.util.List;

public class ExpressionTokenizer {
    /**
     * Takes an expression string and returns a list of tokens to be used for evaluation
     */
    public static List<ExpressionToken> tokenize(String expression) {
        List<ExpressionToken> tokens = new ArrayList<>();
        String currentOperand = "";
        // Go through each character in the expression string and determine if it is an operator or part of an operand
        for (char c : expression.toCharArray()) {
            if (ExpressionOperators.isOperator(c)) {
                if (!currentOperand.isEmpty()) {
                    tokens.add(ExpressionToken.operand(currentOperand));
                    currentOperand = "";
                }
                tokens.add(ExpressionToken.operator(Character.toString(c)));
            } else if (c != ' ') {
                currentOperand += c;
            }
        }
        if (!currentOperand.isEmpty()) {
            tokens.add(ExpressionToken.operand(currentOperand));
        }
        return tokens;
    }
}
