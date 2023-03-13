package ast_elements;

public class BooleanExpression extends Expression {

    private Boolean value;

    public BooleanExpression(Boolean value) {
        System.out.println("Boolean expression constructor " + value);
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
