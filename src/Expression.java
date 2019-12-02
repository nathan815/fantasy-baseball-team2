import java.util.List;

import static java.util.stream.Collectors.joining;

public class Expression {

    private List<ExpressionToken> tokens;

    public Expression(List<ExpressionToken> tokens) {
        this.tokens = tokens;
    }

    public List<ExpressionToken> getTokens() {
        return tokens;
    }

    @Override
    public String toString() {
        return tokens.stream().map(t -> t.value).collect(joining(" "));
    }

}
