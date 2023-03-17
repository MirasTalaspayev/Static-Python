package ast_elements;

public class BooleanExpression extends Expression {

    private Boolean value;

    public BooleanExpression(Boolean value) {
        System.out.println("Boolean expression constructor " + value);
        this.value = value;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.value);
        return sb.toString();
    }
    
}
