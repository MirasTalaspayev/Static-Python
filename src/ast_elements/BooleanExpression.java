package ast_elements;

public class BooleanExpression extends Expression {

    private Boolean value;

    public BooleanExpression(Boolean value) {
        System.out.println("Boolean expression constructor " + value);
        this.value = value;
    }

    public String toString() {
        return this.value.toString();
    }
    
}
