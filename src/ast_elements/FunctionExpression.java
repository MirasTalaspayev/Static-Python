package ast_elements;

public class FunctionExpression extends Expression {

    private FunctionCall function;
    public FunctionExpression(FunctionCall function) {
        super();
        this.function = function;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'toString'");
    }
    
}
