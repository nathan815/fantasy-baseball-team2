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

}
