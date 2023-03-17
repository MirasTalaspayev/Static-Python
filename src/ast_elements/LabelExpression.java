package ast_elements;

public class LabelExpression extends Expression {

    private String value;

    public LabelExpression(String value) {
        this.value = value;
    }
    
    public String toString() {
        return this.value.toString();
    }
    
}
