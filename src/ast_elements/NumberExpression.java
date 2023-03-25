package ast_elements;

import java.util.Map;

public class NumberExpression extends Expression {

    private Integer value;
    private static final Type type = new VariableType("int");

    public NumberExpression(Integer value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return this.value.toString();
    }

	@Override
	public Type analyzeAndGetType(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map) {
		return type;
	}
}
