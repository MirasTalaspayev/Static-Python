package ast_elements;

public class FloatExpression extends Expression {

    private Float value;

    public FloatExpression(Float value) {
        System.out.println("Float expression constructor " + value);
        this.value = value;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.value);
        return sb.toString();
    }
    
}
