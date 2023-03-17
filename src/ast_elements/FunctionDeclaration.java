package ast_elements;

import java.util.List;

public class FunctionDeclaration extends Declaration {
    private String func_name;
    private List<LocalVarDeclaration> param_list;
    private Type t;
    private List<Statement> body;

    public FunctionDeclaration(String func_name, List<LocalVarDeclaration> param_list, Type t, List<Statement> body) {
        this.func_name = func_name;
        this.param_list = param_list;
        this.t = t;
        this.body = body;

        System.out.println("Function Name === " + this.func_name);
        System.out.println("param_list === " + this.param_list);
    }

    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);
        StringBuilder sb = new StringBuilder();
        sb.append(ind).append("def ").append(this.func_name + "(");
        int size = param_list.size();
        for (int i = 0; i < size - 1; i++) {
            sb.append(param_list.get(i)).append(", ");
        }
        if (size >= 1) {
            sb.append(param_list.get(size - 1));
        }
        sb.append(") -> " + this.t + ":").append("\n");
        for (Statement stmt : this.body) {
            sb.append(stmt.toString(indent + 1));
        }
        return sb;
    }
}
