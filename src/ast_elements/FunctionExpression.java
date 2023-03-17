package ast_elements;

import java.util.List;

public class FunctionExpression extends Expression {

    private String func_name;
    private List<Expression> ex_list;

    public FunctionExpression(String func_name, List<Expression> ex_list) {
        super();
        this.func_name = func_name;
        this.ex_list = ex_list;
        System.out.println("ex_list === " + ex_list);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.func_name + "(");
        int size = ex_list.size();
        for (int i = 0; i < size - 1; i++) {
            sb.append(ex_list.get(i)).append(", ");
        }
        if (size >= 1) {
            sb.append(ex_list.get(size - 1) + ")");
        }
        return sb.toString();
    }
}
