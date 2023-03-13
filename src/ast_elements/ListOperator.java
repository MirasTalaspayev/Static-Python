package ast_elements;

public class ListOperator extends Expression {
    private Expression e1;
    private Expression e2;

    public ListOperator(Expression e1, Expression e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);

        StringBuilder sb = new StringBuilder();
        sb.append(ind).append(this.e1.toString()).append(", ").append(this.e2.toString());

        return sb;
    }

    @Override
    public String toString() {
        return toString(0).toString();
    }
}
