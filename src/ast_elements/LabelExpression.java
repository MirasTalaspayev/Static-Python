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
    public void analyze(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType)
            throws SemanticAnalysisException {
        if (!variable_Map.containsKey(value)) {
            throw new SemanticAnalysisException("Variable " + value + " does not exist");
        }
        if (!variable_Map.get(value).equals(expectedType)) {
            throw new SemanticAnalysisException(value + " is not a instance of " + expectedType);
        }
    }

    @Override
    public Type analyzeAndGetType(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map)
            throws SemanticAnalysisException {
        if (!variable_Map.containsKey(value)) {
            throw new SemanticAnalysisException("Variable " + value + " does not exist");
        }
        return variable_Map.get(value);
    }

    @Override
    public void add(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType,
            Expression ex) throws SemanticAnalysisException {
        this.analyze(variable_Map, func_Map, expectedType);
        ex.analyze(variable_Map, func_Map, expectedType);
    }

    @Override
    public void multiply(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType,
            Expression ex) throws SemanticAnalysisException {
        ex.analyze(variable_Map, func_Map, NumberExpression.TYPE);
        analyze(variable_Map, func_Map, expectedType);
    }

    @Override
    public void isEqual(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType,
            Expression ex) throws SemanticAnalysisException {
        ex.analyze(variable_Map, func_Map, analyzeAndGetType(variable_Map, func_Map));
    }
}
