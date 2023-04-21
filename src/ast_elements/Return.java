package ast_elements;

import java.util.Map;

import Executor.ExecutionException;
import Executor.ReturnFromCall;

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

    @Override
    public void execute(Map<String, Object> variable_Map, Map<String, FunctionDeclaration> func_Map)
            throws ExecutionException, ReturnFromCall {
        if (ex == null) {
            throw new ReturnFromCall(null);
        }
        throw new ReturnFromCall(ex.evaluate(variable_Map, func_Map));
    }
}
