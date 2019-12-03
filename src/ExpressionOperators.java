import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExpressionOperators {
    private static final List<Character> OPERATORS = new ArrayList<>(Arrays.asList('*', '/', '+', '-'));
    private static final List<Integer> OPERATOR_PRECEDENCE = new ArrayList<>(Arrays.asList(2, 2, 1, 1));
    private static final Set<Character> OPERATORS_SET = new HashSet<>(OPERATORS);

    // Returns true if c is an operator
    public static boolean isOperator(char c) {
        return OPERATORS_SET.contains(c);
    }

    // Returns true if op2's precedence is higher than or equal to op1
    public static boolean hasGreaterOrEqualPrecedence(char op1, char op2) {
        int op1Index = OPERATORS.indexOf(op1);
        int op2Index = OPERATORS.indexOf(op2);
        return OPERATOR_PRECEDENCE.get(op2Index) >= OPERATOR_PRECEDENCE.get(op1Index);
    }
}
