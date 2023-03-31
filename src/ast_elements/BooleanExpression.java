package ast_elements;

import java.util.Map;

public class BooleanExpression extends Expression {

    private Boolean value;
    private static final Type type = new VariableType("bool");

    public BooleanExpression(Boolean value) {
        this.value = value;
    }

    public String toString() {
        return this.value.toString();
    }

	@Override
	public Type analyzeAndGetType(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map) {
		return type;
	}
    
}
