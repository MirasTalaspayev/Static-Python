package ast_elements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import Executor.ExecutionException;
import SemanticAnalysis.SemanticAnalysisException;

public class BinaryOperator extends Expression {
    private Expression e1;
    private String op;
    private Expression e2;

    private Type type;

    public BinaryOperator(Expression e1, String op, Expression e2) {
        this.e1 = e1;
        this.op = op;
        this.e2 = e2;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(" + this.e1.toString()).append(" " + this.op + " ").append(this.e2.toString() + ")");
        return sb.toString();
    }

    @Override
    public void analyze(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map, Type expectedType)
            throws SemanticAnalysisException {
        if (op.equals("+")) {
            e1.add(variable_Map, func_Map, expectedType, e2);
        } else if (op.equals("-")) {
            e1.subtract(variable_Map, func_Map, expectedType, e2);
        } else if (op.equals("*")) {
            e1.multiply(variable_Map, func_Map, expectedType, e2);
        } else if (op.equals("/")) {
            e1.divide(variable_Map, func_Map, expectedType, e2);
        } else if (op.equals("|")) {
            e1.union(variable_Map, func_Map, expectedType, e2);
        } else if (op.equals("&")) {
            e1.intersection(variable_Map, func_Map, expectedType, e2);
        } else if (op.equals("^")) {
            e1.xor(variable_Map, func_Map, expectedType, e2);
        } else if (op.equals("in")) {
            if (!expectedType.equals(BooleanExpression.TYPE)) {
                throw new SemanticAnalysisException("membership returns bool not '" + expectedType + "'");
            }
            Type type = e1.analyzeAndGetType(variable_Map, func_Map);
            if (!(e2.analyzeAndGetType(variable_Map, func_Map) instanceof CollectionType)) {
                throw new SemanticAnalysisException(e2 + " does not support membership operand");
            }

            CollectionType expr_type = (CollectionType) e2.analyzeAndGetType(variable_Map, func_Map);
            if (expr_type instanceof DictType) {
                DictType dt = (DictType) expr_type.elements_Type;

                if (!dt.getKey_Type().equals(type)) {
                    throw new SemanticAnalysisException("'" + e1 + "' should be a type of '" + dt.getKey_Type() + "'");
                }

            } else {
                if (!expr_type.elements_Type.equals(type)) {
                    throw new SemanticAnalysisException(
                            "'" + e1 + "' should be a type of '" + expr_type.elements_Type + "'");
                }
            }
        } else if (op.equals("and")) {
            e1.analyze(variable_Map, func_Map, BooleanExpression.TYPE);
            e2.analyze(variable_Map, func_Map, BooleanExpression.TYPE);
        }
        type = expectedType;
    }

    @Override
    public Object evaluate(Map<String, Object> variable_Map, Map<String, FunctionDeclaration> func_Map)
            throws ExecutionException {
        Object value_1 = e1.evaluate(variable_Map, func_Map);
        Object value_2 = e2.evaluate(variable_Map, func_Map);

        if (op.equals("+")) {
            if (value_1 instanceof Integer) {
                return (Integer) value_1 + (Integer) value_2;
            } else if (value_1 instanceof Float) {
                return (Float) value_1 + (Float) value_2;
            } else if (value_1 instanceof ArrayList) {
                ArrayList<Object> list = new ArrayList<>((ArrayList) value_1);
                list.addAll((ArrayList) value_2);
                return list;
            }
        } else if (op.equals("-")) {
            if (value_1 instanceof Integer) {
                return (Integer) value_1 - (Integer) value_2;
            } else if (value_1 instanceof Float) {
                return (Float) value_1 - (Float) value_2;
            } else if (value_1 instanceof HashSet) {
                HashSet<Object> set = new HashSet<>((HashSet) value_1);
                set.removeAll((HashSet) value_2);
                return set;
            }
        } else if (op.equals("*")) {
            if (value_1 instanceof Integer && value_2 instanceof Integer) {
                return (Integer) value_1 * (Integer) value_2;
            } else if (value_1 instanceof Float && value_2 instanceof Float) {
                return (Float) value_1 * (Float) value_2;
            } else if (value_1 instanceof Integer && value_2 instanceof ArrayList) {
                ArrayList<Object> list = new ArrayList<>();
                Integer n = (Integer) value_1;
                for (int i = 0; i < n; i++) {
                    list.addAll((ArrayList) value_2);
                }
                return list;
            } else if (value_2 instanceof Integer && value_1 instanceof ArrayList) {
                ArrayList<Object> list = new ArrayList<>();
                Integer n = (Integer) value_2;
                for (int i = 0; i < n; i++) {
                    list.addAll((ArrayList) value_1);
                }
                return list;
            }
        } else if (op.equals("/")) {
            if (value_1 instanceof Integer) {
                return (Integer) value_1 / (Integer) value_2;
            } else if (value_1 instanceof Float && value_2 instanceof Float) {
                return (Float) value_1 / (Float) value_2;
            }
        } else if (op.equals("|")) {
            Set<Object> set = new HashSet<>();
            set.addAll((HashSet) value_1);
            set.addAll((HashSet) value_2);
            return set;
        } else if (op.equals("&")) {
            Set<Object> set = new HashSet<>((HashSet) value_1);
            set.retainAll((HashSet) value_2);
            return set;
        } else if (op.equals("^")) {
            Set<Object> xorSet = new HashSet<>((HashSet) value_1);
            xorSet.addAll((HashSet) value_2);

            Set<Object> commonElements = new HashSet<>((HashSet) value_1);
            commonElements.retainAll((HashSet) value_2);
            xorSet.removeAll(commonElements);

            return xorSet;
        }
        return null;
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
        this.analyze(variable_Map, func_Map, expectedType);
        ex.analyze(variable_Map, func_Map, NumberExpression.TYPE);
    }
}
