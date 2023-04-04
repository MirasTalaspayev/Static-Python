package ast_elements;

import java.util.Map;

import SemanticAnalysis.SemanticAnalysisException;

public class StringExpression extends Expression {

    private String value;
    private static final Type type = new VariableType("str");
    
    public StringExpression(String value) {
        System.out.println("String expression constructor " + value);
        this.value = value;
    }

    public String toString() {
        return this.value.toString();
    }

    @Override
    public void analyzeAndGetType(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType) throws SemanticAnalysisException {
        if (!type.equals(expectedType)) {
            throw new SemanticAnalysisException(this + " is not an instance of " + expectedType);
        }
    }
    
}
