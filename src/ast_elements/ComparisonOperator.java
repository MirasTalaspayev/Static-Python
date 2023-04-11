package ast_elements;

import java.util.Map;

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
        sb.append(this.e1.toString()).append(" " + this.op + " ").append(this.e2.toString());
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
        } 
        else if (op.equals("!=")) {
            e1.notEqual(variable_Map, func_Map, expectedType, e2);
        }
    }
}
