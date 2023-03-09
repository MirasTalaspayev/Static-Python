package ast_elements;

public class Declaration extends Statement {

    private String value;
    private String type;

    public Declaration(String value, String type) {
        this.value = value;
        this.type = type;
        System.out.println("VALUE === " + value);
    }

    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);

        StringBuilder sb = new StringBuilder();
        sb.append(ind).append(this.value + ":").append(this.type).append("\n");

        return sb;
    }
}
