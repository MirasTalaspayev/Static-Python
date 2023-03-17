package ast_elements;

public class FloatExpression extends Expression {

    private Float value;

    public FloatExpression(Float value) {
        System.out.println("Float expression constructor " + value);
        this.value = value;
    }

    public String toString() {
        return this.value.toString();
    }
    
}
