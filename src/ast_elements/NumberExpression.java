package ast_elements;

public class NumberExpression extends Expression {

    private Integer value;

    public NumberExpression(Integer value) {
        this.value = value;
    }
    
    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);

        StringBuilder sb = new StringBuilder();
        sb.append(ind).append(this.value);

        return sb;
    }

    @Override
    public String toString() {
        return toString(0).toString();
    }
}
