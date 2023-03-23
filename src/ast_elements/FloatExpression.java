package ast_elements;

import java.util.Map;

public class FloatExpression extends Expression {

    private Float value;
    private static final Type type = new VariableType("float");
    public FloatExpression(Float value) {
        System.out.println("Float expression constructor " + value);
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
