package ast_elements;

public class Assignment extends Statement {

    private String value;
    private Expression ex;

    public Assignment(String value, Expression ex) {
        this.value = value;
        this.ex = ex;
        System.out.println("VALUE === " + value);
    }

    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);

        StringBuilder sb = new StringBuilder();
        sb.append(ind).append(this.value + " = ").append(this.ex).append("\n");

        return sb;
    }
}
