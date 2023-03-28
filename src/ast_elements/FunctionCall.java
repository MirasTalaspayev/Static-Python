package ast_elements;

import java.util.List;

public class FunctionCall extends ASTElement {

    private String func_name;
    private List<Expression> ex_list;
    
    public FunctionCall(String func_name, List<Expression> ex_list) {
        this.func_name = func_name;
        this.ex_list = ex_list;
    }

    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);
        StringBuilder sb = new StringBuilder();
        sb.append(ind).append(this.func_name + "(");
        int size = ex_list.size();
        for (int i = 0; i < size - 1; i++) {
            sb.append(ex_list.get(i)).append(", ");
        }
        if (size >= 1) {
            sb.append(ex_list.get(size - 1) + ")\n");
        }
        return sb;
    }
}