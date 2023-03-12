package ast_elements;

import java.util.List;

public class ForLoop extends Statement {

    private Expression list;
    private List<Statement> body;

    public ForLoop(Expression list, List<Statement> body) {
        this.list = list;
        this.body = body;
    }

    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);

        StringBuilder sb = new StringBuilder();
        sb.append(ind).append("for ").append(this.list.toString()).append(":\n");

        for (Statement stmt : this.body) {
            sb.append(stmt.toString(indent + 1));
        }

        return sb;
    }
}
