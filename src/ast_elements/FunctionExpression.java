package ast_elements;

import java.util.Map;

import Executor.ExecutionException;
import SemanticAnalysis.SemanticAnalysisException;

public class FunctionExpression extends Expression {

    private FunctionCall func_call;

    public FunctionExpression(FunctionCall func_call) {
        this.func_call = func_call;
    }

    @Override
    public String toString() {
        return func_call.toString(0).toString();
    }

	@Override
	public void analyze(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType) throws SemanticAnalysisException {
        func_call.analyze(variable_Map, func_Map, expectedType);
    }
    @Override
    public Type analyzeAndGetType(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map) throws SemanticAnalysisException {
        return func_call.analyzeAndGetType(variable_Map, func_Map);
    }

    @Override
    public Object evaluate(Map<String, Object> variable_Map, Map<String, FunctionDeclaration> func_Map)
            throws ExecutionException {
        return func_call.evaluate(variable_Map, func_Map);
    }

    @Override
    public void add(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType,
            Expression ex) throws SemanticAnalysisException {
        if (!LabelExpression.ADDABLES.contains(expectedType.getClass())) {
            throw new SemanticAnalysisException(expectedType + " does not support operand '+'");
        }
        this.analyze(variable_Map, func_Map, expectedType);
        ex.analyze(variable_Map, func_Map, expectedType);
    }

    @Override
    public void subtract(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType,
            Expression ex) throws SemanticAnalysisException {
        if (!(expectedType.equals(NumberExpression.TYPE) || expectedType.equals(FloatExpression.TYPE)
                || expectedType instanceof SetType)) {
            throw new SemanticAnalysisException(expectedType + " does not support operand '-'");
        }
        this.analyze(variable_Map, func_Map, expectedType);
        ex.analyze(variable_Map, func_Map, expectedType);
    }

    @Override
    public void multiply(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType,
            Expression ex) throws SemanticAnalysisException {
        if (!LabelExpression.ADDABLES.contains(expectedType.getClass())) {
            throw new SemanticAnalysisException(expectedType + " does not support operand '+'");
        }
        this.analyze(variable_Map, func_Map, expectedType);
        ex.analyze(variable_Map, func_Map, NumberExpression.TYPE);
    }

    @Override
    public void divide(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType,
            Expression ex) throws SemanticAnalysisException {
        if (!(expectedType.equals(NumberExpression.TYPE) || expectedType.equals(FloatExpression.TYPE))) {
            throw new SemanticAnalysisException(expectedType + " does not support operand '/'");
        }
        this.analyze(variable_Map, func_Map, expectedType);
        ex.analyze(variable_Map, func_Map, analyzeAndGetType(variable_Map, func_Map));
    }

    @Override
    public void isEqual(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType,
            Expression ex) throws SemanticAnalysisException {
        ex.analyze(variable_Map, func_Map, analyzeAndGetType(variable_Map, func_Map));
    }

    public void greater(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType,
            Expression ex) throws SemanticAnalysisException {
        if (expectedType instanceof DictType) {
            throw new SemanticAnalysisException(this + " and " + ex + " does not support operand '>'");
        }
        ex.analyze(variable_Map, func_Map, analyzeAndGetType(variable_Map, func_Map));
    }

    public void greater_or_equal(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map,
            Type expectedType, Expression ex) throws SemanticAnalysisException {
        if (expectedType instanceof DictType) {
            throw new SemanticAnalysisException(this + " and " + ex + " does not support operand '>='");
        }
        ex.analyze(variable_Map, func_Map, analyzeAndGetType(variable_Map, func_Map));
    }

    public void less(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType,
            Expression ex) throws SemanticAnalysisException {
        if (expectedType instanceof DictType) {
            throw new SemanticAnalysisException(this + " and " + ex + " does not support operand '<'");
        }
        ex.analyze(variable_Map, func_Map, analyzeAndGetType(variable_Map, func_Map));
    }

    public void less_or_equal(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map,
            Type expectedType, Expression ex) throws SemanticAnalysisException {
        if (expectedType instanceof DictType) {
            throw new SemanticAnalysisException(this + " and " + ex + " does not support operand '<='");
        }
        ex.analyze(variable_Map, func_Map, analyzeAndGetType(variable_Map, func_Map));
    }

    @Override
    public void union(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType,
            Expression ex) throws SemanticAnalysisException {
        if (!(expectedType instanceof SetType)) {
            throw new SemanticAnalysisException(expectedType + " does not support '|' operand.");
        }
        this.analyze(variable_Map, func_Map, expectedType);
        ex.analyze(variable_Map, func_Map, expectedType);
    }

    public void intersection(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map,
            Type expectedType, Expression ex) throws SemanticAnalysisException {
        if (!(expectedType instanceof SetType)) {
            throw new SemanticAnalysisException(expectedType + " does not support '&' operand.");
        }
        this.analyze(variable_Map, func_Map, expectedType);
        ex.analyze(variable_Map, func_Map, expectedType);
    }

    public void xor(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType,
            Expression ex) throws SemanticAnalysisException {
        if (!(expectedType instanceof SetType)) {
            throw new SemanticAnalysisException(expectedType + " does not support '^' operand.");
        }
        this.analyze(variable_Map, func_Map, expectedType);
        ex.analyze(variable_Map, func_Map, expectedType);
    }

    public void membership(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType,
            Expression ex) throws SemanticAnalysisException {
        if (!(ex.analyzeAndGetType(variable_Map, func_Map) instanceof CollectionType)) {
            throw new SemanticAnalysisException(ex + " does not support membership operand");
        }

        CollectionType expr_type = (CollectionType) ex.analyzeAndGetType(variable_Map, func_Map);
        expr_type.elements_Type.equals(this.analyzeAndGetType(variable_Map, func_Map));
    }
}