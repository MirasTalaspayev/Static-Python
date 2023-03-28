package ast_elements;

import java.util.List;
import java.util.Map;

public class FunctionExpression extends Expression {

    private FunctionCall func_call;

    public FunctionExpression(FunctionCall func_call) {
        this.func_call = func_call;
    }

    @Override
    public String toString() {
        return func_call.toString(0).toString();
    }

	@Override
	public Type analyzeAndGetType(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'analyzeAndGetType'");
	}
}
