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
	public Type analyzeAndGetType(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map) throws SemanticAnalysisException {
		if (!variable_Map.containsKey(value)) {
            throw new SemanticAnalysisException("Variable " + value + " does not exist");
        }
        return variable_Map.get(value);
	}
    
}
