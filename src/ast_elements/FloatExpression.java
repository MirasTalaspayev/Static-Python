package ast_elements;

import java.util.Map;

import SemanticAnalysis.SemanticAnalysisException;

public class FloatExpression extends Expression {

    private Float value;
    private static final Type type = new VariableType("float");
    public FloatExpression(Float value) {
        this.value = value;
    }

    public String toString() {
        return this.value.toString();
    }

	@Override
	public void analyze(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType) throws SemanticAnalysisException {
        if (!type.equals(expectedType)) {
            throw new SemanticAnalysisException(this + " is not an instance of " + expectedType);
        }
    }
}
