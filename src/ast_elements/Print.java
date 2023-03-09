package ast_elements;

public class Print extends Statement {

    private Expression ex;

    public Print(Expression ex) {
        this.ex = ex;
        System.out.println("Print === " + ex);
    }

    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);

        StringBuilder sb = new StringBuilder();
        sb.append(ind).append("print(" + this.ex + ")").append("\n");

        return sb;
    }
}
