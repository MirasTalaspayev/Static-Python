package ast_elements;

import java.util.Map;

import Executor.ExecutionException;
import SemanticAnalysis.SemanticAnalysisException;

public class FloatExpression extends Expression {

    private Float value;
    public static final Type TYPE = new VariableType("float");
    public FloatExpression(Float value) {
        this.value = value;
    }

    public String toString() {
        return this.value.toString();
    }

	@Override
	public void analyze(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType) throws SemanticAnalysisException {
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
        return value;
    }

    @Override
    public void add(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType,
            Expression ex) throws SemanticAnalysisException {
        analyze(variable_Map, func_Map, expectedType);
        ex.analyze(variable_Map, func_Map, FloatExpression.TYPE);
    }

    @Override
    public void subtract(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType,
            Expression ex) throws SemanticAnalysisException {
        analyze(variable_Map, func_Map, expectedType);
        ex.analyze(variable_Map, func_Map, FloatExpression.TYPE);
    }

    @Override
    public void multiply(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType,
            Expression ex) throws SemanticAnalysisException {
        analyze(variable_Map, func_Map, expectedType);
        ex.analyze(variable_Map, func_Map, FloatExpression.TYPE);
    }

    @Override
    public void divide(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType,
            Expression ex) throws SemanticAnalysisException {
        analyze(variable_Map, func_Map, expectedType);
        ex.analyze(variable_Map, func_Map, FloatExpression.TYPE);
    }

    @Override
    public void isEqual(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType,
            Expression ex) throws SemanticAnalysisException {
        ex.analyze(variable_Map, func_Map, FloatExpression.TYPE);
    }

    public void greater(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType, Expression ex) throws SemanticAnalysisException {
        ex.analyze(variable_Map, func_Map, FloatExpression.TYPE);
    }

    public void greater_or_equal(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType, Expression ex) throws SemanticAnalysisException {
        ex.analyze(variable_Map, func_Map, FloatExpression.TYPE);
    }

    public void less(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType, Expression ex) throws SemanticAnalysisException {
        ex.analyze(variable_Map, func_Map, FloatExpression.TYPE);
    }

    public void less_or_equal(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType, Expression ex) throws SemanticAnalysisException {
        ex.analyze(variable_Map, func_Map, FloatExpression.TYPE);
    }
}
