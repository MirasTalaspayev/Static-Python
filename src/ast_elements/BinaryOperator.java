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
        else if (op.equals("&")) {
            e1.intersection(variable_Map, func_Map, expectedType, e2);
        }
        else if (op.equals("^")) {
            e1.xor(variable_Map, func_Map, expectedType, e2);
        }
        else if (op.equals("in")) {
            if (!expectedType.equals(BooleanExpression.TYPE)) {
                throw new SemanticAnalysisException("membership returns bool not '" + expectedType + "'");
            }
            Type type = e1.analyzeAndGetType(variable_Map, func_Map);
            if (!(e2.analyzeAndGetType(variable_Map, func_Map) instanceof CollectionType)) {
                throw new SemanticAnalysisException(e2 + " does not support membership operand");
            }
    
            CollectionType expr_type = (CollectionType) e2.analyzeAndGetType(variable_Map, func_Map);
            if (expr_type instanceof DictType) {
                DictType dt = (DictType)expr_type.elements_Type;
                
                if (!dt.getKey_Type().equals(type)) {
                    throw new SemanticAnalysisException("'" + e1 + "' should be a type of '" + dt.getKey_Type() + "'");
                }
                
            }
            else {
                if (!expr_type.elements_Type.equals(type)) {
                    throw new SemanticAnalysisException("'" + e1 + "' should be a type of '" + expr_type.elements_Type + "'");
                }
            }
        }
        else if (op.equals("and")) {
            e1.analyze(variable_Map, func_Map, BooleanExpression.TYPE);
            e2.analyze(variable_Map, func_Map, BooleanExpression.TYPE);
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
