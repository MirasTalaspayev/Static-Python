package ast_elements;

import java.util.Map;

public class StringExpression extends Expression {

    private String value;
    private static final Type type = new VariableType("str");
    
    public StringExpression(String value) {
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
