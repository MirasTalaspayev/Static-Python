package ast_elements;

public class NumberExpression extends Expression {

    private Integer value;

    public NumberExpression(Integer value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return this.value.toString();
    }
}
