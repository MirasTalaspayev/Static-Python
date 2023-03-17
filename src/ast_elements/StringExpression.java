package ast_elements;

public class StringExpression extends Expression {

    private String value;

    public StringExpression(String value) {
        System.out.println("String expression constructor " + value);
        this.value = value;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.value);
        return sb.toString();
    }
    
}
