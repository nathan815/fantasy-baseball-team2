import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressionOperatorsTest {

    @Test
    public void hasPrecedence_ShouldReturnTrue() {
        assertTrue(ExpressionOperators.hasGreaterOrEqualPrecedence('+', '*'));
        assertTrue(ExpressionOperators.hasGreaterOrEqualPrecedence('+', '/'));

        assertTrue(ExpressionOperators.hasGreaterOrEqualPrecedence('-', '*'));
        assertTrue(ExpressionOperators.hasGreaterOrEqualPrecedence('-', '/'));

        assertTrue(ExpressionOperators.hasGreaterOrEqualPrecedence('+', '-'));
        assertTrue(ExpressionOperators.hasGreaterOrEqualPrecedence('*', '/'));
    }

    @Test
    public void hasPrecedence_ShouldReturnFalse() {
        assertFalse(ExpressionOperators.hasGreaterOrEqualPrecedence('*', '+'));
        assertFalse(ExpressionOperators.hasGreaterOrEqualPrecedence('*', '-'));

        assertFalse(ExpressionOperators.hasGreaterOrEqualPrecedence('/', '+'));
        assertFalse(ExpressionOperators.hasGreaterOrEqualPrecedence('/', '-'));
    }
}
