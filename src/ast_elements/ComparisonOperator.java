package ast_elements;

import java.util.Map;

public class ComparisonOperator extends Expression {
    private Expression e1;
    private String op;
    private Expression e2;

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
	public Type analyzeAndGetType(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'analyzeAndGetType'");
	}
}
