package ast_elements;

public class NumberExpression extends Expression {

    private String value;
    public NumberExpression(String value) {
        System.out.println("Number expression constructor " + value);
        this.value = value;

    }
    
    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);

        StringBuilder sb = new StringBuilder();
        sb.append(ind).append(this.value).append(":\n");

        return sb;
    }

    public String toString() {
        // String s = new String(String.valueOf(e1) + op + String.valueOf(e2));
		// String s = String.join("", String.valueOf(e1), op, String.valueOf(e2));
        return toString(0).toString();
    }
    
}
