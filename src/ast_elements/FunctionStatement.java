package ast_elements;

import java.util.List;
import java.util.Map;

public class FunctionStatement extends Statement {

    private String func_name;
    private List<Expression> ex_list;

    public FunctionStatement(String func_name, List<Expression> ex_list) {
        super();
        this.func_name = func_name;
        this.ex_list = ex_list;
        System.out.println("ex_list === " + ex_list);
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

    @Override
    public void analyze(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'analyze'");
    }
}
