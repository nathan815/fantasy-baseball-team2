import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressionOperatorsTest {

    @Test
    public void hasPrecedence_ShouldReturnTrue() {
        assertTrue(ExpressionOperators.hasPrecedence('+', '*'));
        assertTrue(ExpressionOperators.hasPrecedence('+', '/'));

        assertTrue(ExpressionOperators.hasPrecedence('-', '*'));
        assertTrue(ExpressionOperators.hasPrecedence('-', '/'));
    }

    @Test
    public void hasPrecedence_ShouldReturnFalse() {
        assertFalse(ExpressionOperators.hasPrecedence('*', '+'));
        assertFalse(ExpressionOperators.hasPrecedence('*', '-'));

        assertFalse(ExpressionOperators.hasPrecedence('/', '+'));
        assertFalse(ExpressionOperators.hasPrecedence('/', '-'));

        assertFalse(ExpressionOperators.hasPrecedence('+', '-'));
        assertFalse(ExpressionOperators.hasPrecedence('*', '/'));
    }
}
