package ast_elements;

public class Return extends Statement {

    private Expression ex;

    public Return(Expression ex) {
        this.ex = ex;
        System.out.println("Return === " + ex);
    }

    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);
        StringBuilder sb = new StringBuilder();
        sb.append(ind).append("return " + this.ex).append("\n");
        return sb;
    }
}
