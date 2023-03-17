package ast_elements;

public class LabelExpression extends Expression {

    private String value;

    public LabelExpression(String value) {
        this.value = value;
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.value);
        return sb.toString();
    }
    
}
