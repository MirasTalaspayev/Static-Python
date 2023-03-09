package ast_elements;

public class BinaryOperator extends Expression {
    private Expression e1;
    private String op;
    private Expression e2;
    public BinaryOperator(Expression e1, String op, Expression e2) {
        System.out.println("Binary operator expression constructor");
        this.e1 = e1;
        this.op = op;
        this.e2 = e2;
        System.out.println("e1 = " + e1);
        System.out.println("op = " + op);
        System.out.println("e2 = " + e2);
    }

    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);

        StringBuilder sb = new StringBuilder();
        sb.append(ind).append(this.e1.toString()).append(this.op).append(this.e2.toString()).append("\n");

        return sb;
    }

    // @Override
    public String toString() {
        // String s = new String(String.valueOf(e1) + op + String.valueOf(e2));
		// String s = String.join("", String.valueOf(e1), op, String.valueOf(e2));
        return toString(0).toString();
    }
}
