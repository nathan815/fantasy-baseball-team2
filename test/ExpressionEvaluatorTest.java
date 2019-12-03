import java.util.Arrays;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class ExpressionEvaluatorTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    private Hitter hitter;
    private Pitcher pitcher;

    @Before
    public void beforeEach() {
        hitter = new Hitter("John", "Doe", "team1", "SS", 0.5, 5, 10, 0);
        pitcher = new Pitcher("Some", "One", "team2", 0.5, 25, 100, 0);
    }

    @Test
    public void evaluate_SingleConstantExpr_ShouldReturnConstant() throws ExpressionEvaluationException {
        // Simple single constant should simply output same constant
        Expression expr = new Expression(Arrays.asList(ExpressionToken.operand("10.0")));
        assertEquals(10.0, new ExpressionEvaluator(expr).evaluate(hitter), 0.0);
    }

    @Test
    public void evaluate_SingleHitterOperandExpr_ShouldReturnCorrectValue() throws ExpressionEvaluationException {
        // Simple single operand expression "AVG" should simply output the hitter's average
        Expression expr = new Expression(Arrays.asList(ExpressionToken.operand("AVG")));
        assertEquals(hitter.getAvg(), new ExpressionEvaluator(expr).evaluate(hitter), 0.0);
    }

    @Test
    public void evaluate_SinglePitcherOperandExpr_ShouldReturnCorrectValue() throws ExpressionEvaluationException {
        // Simple single operand expression "AVG" should simply output the hitter's average
        Expression expr = new Expression(Arrays.asList(ExpressionToken.operand("ERA")));
        assertEquals(pitcher.getEarnedRunAverage(), new ExpressionEvaluator(expr).evaluate(pitcher), 0.0);
    }

    @Test
    public void evaluate_SimpleAllAdditionExpr_ShouldReturnCorrectValue() throws ExpressionEvaluationException {
        double expectedOutput = hitter.getAvg() + hitter.getHits() + hitter.getRuns();
        // Expression: 'AVG + H + R' (avg + hits + runs)
        Expression expr = new Expression(Arrays.asList(
                ExpressionToken.operand("AVG"),
                ExpressionToken.operator("+"),
                ExpressionToken.operand("H"),
                ExpressionToken.operator("+"),
                ExpressionToken.operand("R")
        ));
        assertEquals(expectedOutput, new ExpressionEvaluator(expr).evaluate(hitter), 0.0);
    }

    @Test
    public void evaluate_SimpleAllMinusExpr_ShouldReturnCorrectValue() throws ExpressionEvaluationException {
        double expectedOutput = hitter.getAvg() - hitter.getHits() - hitter.getRuns();
        // Expression: 'AVG - H - R' (avg - hits - runs)
        Expression expr = new Expression(Arrays.asList(
                ExpressionToken.operand("AVG"),
                ExpressionToken.operator("-"),
                ExpressionToken.operand("H"),
                ExpressionToken.operator("-"),
                ExpressionToken.operand("R")
        ));
        assertEquals(expectedOutput, new ExpressionEvaluator(expr).evaluate(hitter), 0.0);
    }

    @Test
    public void evaluate_AddThenDivideExpr_ShouldFollowPrecedenceRules() throws ExpressionEvaluationException {
        double expectedOutput = pitcher.getStrikeOuts() + (double) pitcher.getHitsAllowed() / pitcher.getStrikeOuts() + 1;
        // Expression: 'SO + HA / SO + 1' (strike outs + hits allowed / strike outs + 1)
        Expression expr = new Expression(Arrays.asList(
                ExpressionToken.operand("SO"),
                ExpressionToken.operator("+"),
                ExpressionToken.operand("HA"),
                ExpressionToken.operator("/"),
                ExpressionToken.operand("SO"),
                ExpressionToken.operator("+"),
                ExpressionToken.operand("1")
        ));
        assertEquals(expectedOutput, new ExpressionEvaluator(expr).evaluate(pitcher), 0.0);
    }

    @Test
    public void evaluate_AddThenMinusExpr_ShouldEvalLeftToRight() throws ExpressionEvaluationException {
        double expectedOutput = pitcher.getStrikeOuts() + (double) pitcher.getHitsAllowed() - 50;
        // Expression: 'SO + HA - 50' (strike outs + hits allowed - 50)
        Expression expr = new Expression(Arrays.asList(
                ExpressionToken.operand("SO"),
                ExpressionToken.operator("+"),
                ExpressionToken.operand("HA"),
                ExpressionToken.operator("-"),
                ExpressionToken.operand("50")
        ));
        assertEquals(expectedOutput, new ExpressionEvaluator(expr).evaluate(pitcher), 0.0);
    }

    @Test
    public void evaluate_InvalidStatForPlayer_ShouldThrowException() throws ExpressionEvaluationException {
        // hitter does not have SO (strike outs) so should throw exception
        Expression expr = new Expression(Arrays.asList(ExpressionToken.operand("SO")));

        exception.expect(ExpressionEvaluationException.class);
        new ExpressionEvaluator(expr).evaluate(hitter);
    }

    @Test
    public void evaluate_NonexistentStat_ShouldThrowException() throws ExpressionEvaluationException {
        // stat HELLO does not exist
        Expression expr = new Expression(Arrays.asList(ExpressionToken.operand("HELLO")));

        exception.expect(ExpressionEvaluationException.class);
        new ExpressionEvaluator(expr).evaluate(hitter);
    }
}
