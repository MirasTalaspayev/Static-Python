package ast_elements;

import java.util.ArrayList;
import java.util.Map;

import Executor.ExecutionException;
import SemanticAnalysis.SemanticAnalysisException;

public class ComparisonOperator extends Expression {
    private Expression e1;
    private String op;
    private Expression e2;

    private static final Type TYPE = new VariableType("bool");

    public ComparisonOperator(Expression e1, String op, Expression e2) {
        this.e1 = e1;
        this.op = op;
        this.e2 = e2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(this.e1.toString()).append(" " + this.op + " ").append(this.e2.toString()).append(")");
        return sb.toString();
    }

    @Override
    public void analyze(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType)
            throws SemanticAnalysisException {
        if (!expectedType.equals(TYPE)) {
            throw new SemanticAnalysisException(this + " should be a type of " + TYPE);
        }
        if (op.equals("==")) {
            e1.isEqual(variable_Map, func_Map, expectedType, e2);
        } else if (op.equals("!=")) {
            e1.notEqual(variable_Map, func_Map, expectedType, e2);
        } else if (op.equals(">")) {
            e1.greater(variable_Map, func_Map, expectedType, e2);
        } else if (op.equals(">=")) {
            e1.greater_or_equal(variable_Map, func_Map, expectedType, e2);
        } else if (op.equals("<")) {
            e1.less(variable_Map, func_Map, expectedType, e2);
        } else if (op.equals("<=")) {
            e1.less_or_equal(variable_Map, func_Map, expectedType, e2);
        }
    }

    @Override
    public Object evaluate(Map<String, Object> variable_Map, Map<String, FunctionDeclaration> func_Map)
            throws ExecutionException {
        Object value_1 = e1.evaluate(variable_Map, func_Map);
        Object value_2 = e2.evaluate(variable_Map, func_Map);
        
        if (op.equals("==")) {
            return value_1.equals(value_2);
        } else if (op.equals("!=")) {
            return !value_1.equals(value_2);
        } else if (op.equals(">")) {
            if (value_1 instanceof Integer) {
                return (Integer)value_1 > (Integer)value_2;
            }
            else if (value_1 instanceof Float) {
                return (Float)value_1 > (Float)value_2;
            }
        } else if (op.equals(">=")) {
            if (value_1 instanceof Integer) {
                return (Integer)value_1 >= (Integer)value_2;
            }
            else if (value_1 instanceof Float) {
                return (Float)value_1 >= (Float)value_2;
            }
        } else if (op.equals("<")) {
            if (value_1 instanceof Integer) {
                return (Integer)value_1 < (Integer)value_2;
            }
            else if (value_1 instanceof Float) {
                return (Float)value_1 < (Float)value_2;
            }
        } else if (op.equals("<=")) {
            if (value_1 instanceof Integer) {
                return (Integer)value_1 <= (Integer)value_2;
            }
            else if (value_1 instanceof Float) {
                return (Float)value_1 <= (Float)value_2;
            }
        }
        return null;
    }
}
