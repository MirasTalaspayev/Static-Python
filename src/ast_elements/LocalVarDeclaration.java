package ast_elements;

public class LocalVarDeclaration extends Declaration {
    private String var_name;
    private Type type;

    public LocalVarDeclaration(String var_name, Type type) {
        this.var_name = var_name;
        this.type = type;
    }

    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);
        StringBuilder sb = new StringBuilder();
        sb.append(ind).append(this.var_name + ":").append(this.type);
        return sb;
    }
}
