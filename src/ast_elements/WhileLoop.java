package ast_elements;

import java.util.List;

public class WhileLoop extends Statement {

    private Expression cond;
    private List<Statement> body;

    public WhileLoop(Expression cond, List<Statement> body) {
        this.cond = cond;
        this.body = body;
        System.out.println("CONDITION === " + cond.toString());
    }

    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);

        StringBuilder sb = new StringBuilder();
        sb.append(ind).append("while ").append(this.cond.toString()).append(":\n");

        for (Statement stmt : this.body) {
            sb.append(stmt.toString(indent + 1));
        }

        return sb;
    }
}
