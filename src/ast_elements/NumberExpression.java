package ast_elements;

import java.util.Map;

import SemanticAnalysis.SemanticAnalysisException;

public class NumberExpression extends Expression {

    private Integer value;
    public static final Type TYPE = new VariableType("int");

    public NumberExpression(Integer value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return this.value.toString();
    }

	@Override
	public void analyze(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType) throws SemanticAnalysisException {
        if (!TYPE.equals(expectedType)) {
            throw new SemanticAnalysisException(this + " is not an instance of " + expectedType);
        }
    }
}
