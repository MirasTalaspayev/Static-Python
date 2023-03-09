package ast_elements;

public class FloatExpression extends Expression {

    private Float value;

    public FloatExpression(Float value) {
        System.out.println("Float expression constructor " + value);
        this.value = value;
    }
    
    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);

        StringBuilder sb = new StringBuilder();
        sb.append(ind).append(this.value);

        return sb;
    }

    public String toString() {
        return toString(0).toString();
    }
    
}
