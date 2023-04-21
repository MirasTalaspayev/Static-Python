package ast_elements;

import java.util.Map;

import Executor.ExecutionException;
import SemanticAnalysis.SemanticAnalysisException;

public class BooleanExpression extends Expression {

    private Boolean value;
    public static final Type TYPE = new VariableType("bool");

    public BooleanExpression(Boolean value) {
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
        return value;
    }
}
