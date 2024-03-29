package ast_elements;

import java.util.Map;

import Executor.ExecutionException;
import SemanticAnalysis.SemanticAnalysisException;

public class StringExpression extends Expression {

    private String value;
    public static final Type TYPE = new VariableType("str");

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
    public Object evaluate(Map<String, Object> variable_Map, Map<String, FunctionDeclaration> func_Map)
            throws ExecutionException {
        return value.subSequence(1, value.length() - 1);
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
