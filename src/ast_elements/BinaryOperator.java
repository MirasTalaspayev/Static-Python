package ast_elements;

import java.util.Map;

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
        } 
        else if (op.equals("-")) {
            e1.subtract(variable_Map, func_Map, expectedType, e2);
        }
        else if (op.equals("*")) {
            e1.multiply(variable_Map, func_Map, expectedType, e2);
        }
        else if (op.equals("|")) {
            e1.union(variable_Map, func_Map, expectedType, e2);
        }
        else if (op.equals("in")) {
            e1.membership(variable_Map, func_Map, BooleanExpression.TYPE, e2);
        }
        type = expectedType;
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
