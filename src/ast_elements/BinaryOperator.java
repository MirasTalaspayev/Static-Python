package ast_elements;

public class BinaryOperator extends Expression {
    private Expression e1;
    private String op;
    private Expression e2;
    public BinaryOperator(Expression e1, String op, Expression e2) {
        this.e1 = e1;
        this.op = op;
        this.e2 = e2;
    }

    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);

        StringBuilder sb = new StringBuilder();
        sb.append(ind).append(this.e1.toString()).append(" " + this.op + " ").append(this.e2.toString());

        return sb;
    }

    @Override
    public String toString() {
        return toString(0).toString();
    }
}
