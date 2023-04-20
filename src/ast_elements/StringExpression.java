package ast_elements;

import java.util.Map;

import SemanticAnalysis.SemanticAnalysisException;

public class StringExpression extends Expression {

    private String value;
    private static final Type TYPE = new VariableType("str");

    public StringExpression(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value.toString();
    }

    @Override
    public void analyze(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType)
            throws SemanticAnalysisException {
        if (!TYPE.equals(expectedType)) {
            throw new SemanticAnalysisException(this + " is not an instance of " + expectedType);
        }
    }

    @Override
    public Type analyzeAndGetType(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map)
            throws SemanticAnalysisException {
        return TYPE;
    }
    
    @Override
    public void add(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType,
            Expression ex) throws SemanticAnalysisException {
        this.analyze(variable_Map, func_Map, expectedType);
        ex.analyze(variable_Map, func_Map, StringExpression.TYPE);
    }

    @Override
    public void isEqual(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType,
            Expression ex) throws SemanticAnalysisException {
        ex.analyze(variable_Map, func_Map, StringExpression.TYPE);
    }

    public void greater(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType, Expression ex) throws SemanticAnalysisException {
        ex.analyze(variable_Map, func_Map, StringExpression.TYPE);
    }

    public void greater_or_equal(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType, Expression ex) throws SemanticAnalysisException {
        ex.analyze(variable_Map, func_Map, StringExpression.TYPE);
    }

    public void less(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType, Expression ex) throws SemanticAnalysisException {
        ex.analyze(variable_Map, func_Map, StringExpression.TYPE);
    }

    public void less_or_equal(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType, Expression ex) throws SemanticAnalysisException {
        ex.analyze(variable_Map, func_Map, StringExpression.TYPE);
    }
}
