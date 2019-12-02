import java.util.Arrays;
import java.util.List;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ExpressionTokenizerTest {
    @Test
    public void tokenize_ExpressionWithSpacesBetweenEveryToken_ShouldReturnProperTokens() {
        String expr = "AVG * 5.2 / RBI + 4";
        List<ExpressionToken> expectedTokens = Arrays.asList(
                ExpressionToken.operand("AVG"),
                ExpressionToken.operator("*"),
                ExpressionToken.operand("5.2"),
                ExpressionToken.operator("/"),
                ExpressionToken.operand("RBI"),
                ExpressionToken.operator("+"),
                ExpressionToken.operand("4")
        );
        assertThat(ExpressionTokenizer.tokenize(expr), is(expectedTokens));
    }

    @Test
    public void tokenize_ExpressionWithoutSpacesBetweenSomeTokens_ShouldReturnProperTokens() {
        String expr = "3 + HA/10  *RBI + 3.25";
        List<ExpressionToken> expectedTokens = Arrays.asList(
                ExpressionToken.operand("3"),
                ExpressionToken.operator("+"),
                ExpressionToken.operand("HA"),
                ExpressionToken.operator("/"),
                ExpressionToken.operand("10"),
                ExpressionToken.operator("*"),
                ExpressionToken.operand("RBI"),
                ExpressionToken.operator("+"),
                ExpressionToken.operand("3.25")
        );
        assertThat(ExpressionTokenizer.tokenize(expr), is(expectedTokens));
    }
}
