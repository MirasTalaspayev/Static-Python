package ast_elements;

import java.util.Map;

public class Return extends Statement {

    private Expression ex;

    public Return(Expression ex) {
        this.ex = ex;
    }

    public Expression getEx() {
        return ex;
    }

    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);
        StringBuilder sb = new StringBuilder();
        sb.append(ind).append("return " + this.ex).append("\n");
        return sb;
    }

    @Override
    public void analyze(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map) {
        
    }
}
