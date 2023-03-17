package ast_elements;

public class VarDeclaration extends Declaration {
    private String var_name;
    private Type type;
    private Expression ex;

    public VarDeclaration(String var_name, Type type, Expression ex) {
        this.var_name = var_name;
        this.type = type;
        this.ex = ex;
    }

    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);
        StringBuilder sb = new StringBuilder();
        sb.append(ind).append(this.var_name + ":").append(this.type + " = ").append(this.ex).append("\n");
        return sb;
    }
}
