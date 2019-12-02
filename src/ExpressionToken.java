class ExpressionToken {
    enum Type { OPERATOR, OPERAND_NUMERIC, OPERAND_VAR };

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
        return new ExpressionToken(Utils.isNumeric(value) ? Type.OPERAND_NUMERIC : Type.OPERAND_VAR, value);
    }

    public char getOperator() {
        return value.charAt(0);
    }

    public double getNumericValue() {
        return Double.parseDouble(value);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ExpressionToken)) return false;
        ExpressionToken other = (ExpressionToken)obj;
        return type == other.type && value.equals(other.value);
    }
}
