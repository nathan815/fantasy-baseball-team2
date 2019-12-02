import java.util.Map;
import java.util.Stack;

public class ExpressionEvaluator {
    private Expression expression;

    public ExpressionEvaluator(Expression expression) {
        this.expression = expression;
    }

    // Evaluate this expression for a given player
    // Algorithm Reference: https://www.geeksforgeeks.org/expression-evaluation/
    public double evaluate(Player player) throws ExpressionEvaluationException {
        Stack<Double> values = new Stack<>();
        Stack<ExpressionToken> operators = new Stack<>();
        Map<String, Double> statValues = player.getStatValuesMap();

        for(ExpressionToken token : expression.getTokens()) {
            if(token.type == ExpressionToken.Type.OPERAND_NUMERIC) {
                // we have a numeric value so we can just push it directly to the values stack
                values.push(token.getNumericValue());
            } else if(token.type == ExpressionToken.Type.OPERAND_VAR) {
                // does this variable exist?
                if(!statValues.containsKey(token.name)) {
                    throw new ExpressionEvaluationException("Invalid stat: " + token.name);
                }
                // push the numeric value of this "variable" (stat value for player) to the values stack
                values.push(statValues.get(token.name));
            } else if(token.type == ExpressionToken.Type.OPERATOR) {
                // first apply operators at top of stack with precedence higher than this operator
                while(!operators.isEmpty() && hasPrecedence(token, operators.peek())) {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                // now we add our current operator to the stack
                operators.push(token);
            }
        }

        // Entire expression has been parsed at this point, so apply remaining operators to remaining values
        while(!operators.isEmpty()) {
            values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
        }

        // Top of values stack contains the result
        return values.pop();
    }

    private boolean hasPrecedence(ExpressionToken tok1, ExpressionToken tok2) {
        char op1 = tok1.getOperator();
        char op2 = tok2.getOperator();
        return (op1 == '+' || op1 == '-') && (op2 == '*' || op2 == '/');
    }

    private double applyOperator(ExpressionToken operator, double b, double a) {
        switch(operator.getOperator()) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                return a / b;
        }
        return 0.0;
    }
}
