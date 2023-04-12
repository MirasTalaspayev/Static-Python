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
	public void analyze(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType) throws SemanticAnalysisException {
        func_call.analyze(variable_Map, func_Map, expectedType);
    }
}