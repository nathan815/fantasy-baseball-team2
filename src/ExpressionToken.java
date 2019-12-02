class ExpressionToken {
    enum Type { OPERATOR, OPERAND };

    public Type type;
    public String value;

    public ExpressionToken(Type type, String value) {
        this.type = type;
        this.value = value;
    }

    public static ExpressionToken operator(String value) {
        return new ExpressionToken(ExpressionToken.Type.OPERATOR, value);
    }

    public static ExpressionToken operand(String value) {
        return new ExpressionToken(ExpressionToken.Type.OPERAND, value);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ExpressionToken)) return false;
        ExpressionToken other = (ExpressionToken)obj;
        return type == other.type && value.equals(other.value);
    }
}
