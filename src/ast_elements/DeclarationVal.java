package ast_elements;

public class DeclarationVal extends Statement {

    private String value;
    private String type;
    private Expression ex;

    public DeclarationVal(String value, String type, Expression ex) {
        this.value = value;
        this.type = type;
        this.ex = ex;
        System.out.println("VALUE === " + value);
        System.out.println("TYPE === " + type);
        System.out.println("EXPRESSION = " + ex);
    }

    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);

        StringBuilder sb = new StringBuilder();
        sb.append(ind).append(this.value).append(this.type).append(this.ex).append("\n");

        return sb;
    }
}
