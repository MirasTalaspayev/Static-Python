package ast_elements;

public class StringExpression extends Expression {

    private String value;

    public StringExpression(String value) {
        System.out.println("String expression constructor " + value);
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
