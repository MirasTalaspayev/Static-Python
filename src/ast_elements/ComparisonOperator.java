package ast_elements;

import java.util.Map;

import SemanticAnalysis.SemanticAnalysisException;

public class ComparisonOperator extends Expression {
    private Expression e1;
    private String op;
    private Expression e2;

    private static final Type type = new VariableType("bool");
    
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
	public Type analyzeAndGetType(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map) throws SemanticAnalysisException {
		Type e1_typ1 = e1.analyzeAndGetType(variable_Map, func_Map);
		Type e2_typ1 = e2.analyzeAndGetType(variable_Map, func_Map);
        
        if (op == "==" || op == "!=") {

        }
        else {
            
        }
        return type;
	}
}
