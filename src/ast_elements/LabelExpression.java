package ast_elements;

import java.util.HashSet;
import java.util.Map;

import SemanticAnalysis.SemanticAnalysisException;

public class LabelExpression extends Expression {

    private String value;

    private static final HashSet<Class> ADDABLES = new HashSet<>() {
        {
            add(VariableType.class);
            add(ListType.class);
            add(TupleType.class);
        }
    };

    public LabelExpression(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
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
        if (!ADDABLES.contains(expectedType.getClass())) {
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
        if (!ADDABLES.contains(expectedType.getClass())) {
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
