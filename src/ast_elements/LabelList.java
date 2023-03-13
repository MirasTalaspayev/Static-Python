package ast_elements;

public class LabelList extends ASTElement {
    private String l1;
    private String l2;

    public LabelList(String l1, String l2) {
        this.l1 = l1;
        this.l2 = l2;
    }

    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);

        StringBuilder sb = new StringBuilder();
        sb.append(ind).append(this.l1.toString()).append(", ").append(this.l2.toString());

        return sb;
    }

    @Override
    public String toString() {
        return toString(0).toString();
    }
}
