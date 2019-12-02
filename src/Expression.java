import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Expression {

    private String expressionString;
    private List<ExpressionToken> tokens;

    public Expression(String expressionString) {
        this.expressionString = expressionString;
        this.tokens = Tokenizer.tokenize(expressionString);
    }

    public List<ExpressionToken> getTokens() {
        return tokens;
    }

    @Override
    public String toString() {
        return expressionString;
    }

    public static class Tokenizer {
        private static final Set<Character> operators = new HashSet<>(Arrays.asList('+', '-', '*', '/'));

        /**
         * Takes an expression string and returns a list of tokens to be used for evaluation
         */
        public static List<ExpressionToken> tokenize(String expression) {
            List<ExpressionToken> tokens = new ArrayList<>();
            String currentOperand = "";
            // Go through each character in the expression string and determine if it is an operator or part of an operand
            for(char c : expression.toCharArray()) {
                if(isOperator(c)) {
                    if(!currentOperand.isEmpty()) {
                        tokens.add(new ExpressionToken(ExpressionToken.Type.OPERAND, currentOperand));
                        currentOperand = "";
                    }
                    tokens.add(new ExpressionToken(ExpressionToken.Type.OPERATOR, Character.toString(c)));
                } else if(c != ' ') {
                    currentOperand += c;
                }
            }
            if(!currentOperand.isEmpty()) {
                tokens.add(new ExpressionToken(ExpressionToken.Type.OPERAND, currentOperand));
            }
            return tokens;
        }

        private static boolean isOperator(char c) {
            return operators.contains(c);
        }
    }
}
