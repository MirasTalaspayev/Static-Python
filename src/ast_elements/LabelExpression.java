package ast_elements;

import java.util.Map;

import SemanticAnalysis.SemanticAnalysisException;

public class LabelExpression extends Expression {

    private String value;

    public LabelExpression(String value) {
        this.value = value;
    }
    
    public String toString() {
        return this.value.toString();
    }

	@Override
	public void analyze(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType) throws SemanticAnalysisException {
		if (!variable_Map.containsKey(value)) {
            throw new SemanticAnalysisException("Variable " + value + " does not exist");
        }
        if (!variable_Map.get(value).equals(expectedType)) {
            throw new SemanticAnalysisException(value + " is not a instance of " + expectedType);
        }
    }
    
}
