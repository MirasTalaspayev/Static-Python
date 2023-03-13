package ast_elements;

public class Assignment extends Statement {

    private String var_name;
    private Expression ex;

    public Assignment(String var_name, Expression ex) {
        this.var_name = var_name;
        this.ex = ex;
    }

    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);

        StringBuilder sb = new StringBuilder();
        sb.append(ind).append(this.var_name + " = ").append(this.ex).append("\n");

        return sb;
    }
}
