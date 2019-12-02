class ExpressionToken {
    enum Type { OPERATOR, OPERAND_NUMERIC, OPERAND_VAR };

    public Type type;
    public String name;

    public ExpressionToken(Type type, String value) {
        this.type = type;
        this.name = value;
    }

    public static ExpressionToken operator(String value) {
        return new ExpressionToken(ExpressionToken.Type.OPERATOR, value);
    }

    public static ExpressionToken operand(String value) {
        return new ExpressionToken(Utils.isNumeric(value) ? Type.OPERAND_NUMERIC : Type.OPERAND_VAR, value);
    }

    public char getOperator() {
        return name.charAt(0);
    }

    public double getNumericValue() {
        return Double.parseDouble(name);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof ExpressionToken)) return false;
        ExpressionToken other = (ExpressionToken)obj;
        return type == other.type && name.equals(other.name);
    }

    @Override
    public String toString() {
        return type + ": " + name;
    }
}
