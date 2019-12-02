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
    public void evaluate_SingleOperandExpr_ShouldReturnCorrectValue() throws ExpressionEvaluationException {
        // Simple single operand expression "AVG" should simply output the hitter's average
        Expression expr = new Expression(Arrays.asList(ExpressionToken.operand("AVG")));
        assertEquals(hitter.getAvg(), new ExpressionEvaluator(expr).evaluate(hitter), 0.0);
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
        // AVG - H - R = 0.5 - 5 - 10 = -14.5
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
        // For our sample 'pitcher', SO + HA / SO + 1 = 25 + 100 / 25 = 30.0
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
    public void evaluate_InvalidStatForPlayer_ShouldThrowException() throws ExpressionEvaluationException {
        exception.expect(ExpressionEvaluationException.class);
        // hitter does not have SO (strike outs) so should throw exception
        Expression expr = new Expression(Arrays.asList(ExpressionToken.operand("SO")));
        new ExpressionEvaluator(expr).evaluate(hitter);
    }
}
