package ast_elements;

public class FunctionStatement extends Statement {
    
    private FunctionCall function;
    public FunctionStatement(FunctionCall function) {
        super();
        this.function = function;
    }
    @Override
    public StringBuilder toString(int indent) {
        return function.toString();
    }
    
}
