package ast_elements;

import java.util.Map;

import SemanticAnalysis.SemanticAnalysisException;

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
	public Type analyzeAndGetType(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map) throws SemanticAnalysisException {
        return func_call.analyzeAndGetType(variable_Map, func_Map);
	}
}
