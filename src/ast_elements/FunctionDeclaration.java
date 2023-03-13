package ast_elements;

import java.util.List;

public class FunctionDeclaration extends Declaration {
    private String func_name;
    private Expression ex;
    private List<Statement> body;

    public FunctionDeclaration(String func_name, Expression ex, List<Statement> body) {
        this.func_name = func_name;
        this.ex = ex;
        this.body = body;

        System.out.println("Function Name === " + this.func_name);
        System.out.println("label_list === " + this.ex);
    }

    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);

        StringBuilder sb = new StringBuilder();
        sb.append(ind).append("def ").append(this.func_name).append("(" + this.ex + "):").append("\n");

        for (Statement stmt : this.body) {
            sb.append(stmt.toString(indent + 1));
        }

        return sb;
    }
}
