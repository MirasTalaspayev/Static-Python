package ast_elements;

public class FunctionCall extends Statement {

    private String func_name;
    private Expression ex;

    public FunctionCall(String func_name, Expression ex) {
        this.func_name = func_name;
        this.ex = ex;
        System.out.println("Print === " + ex);
    }

    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);

        StringBuilder sb = new StringBuilder();
        sb.append(ind).append(this.func_name).append("(" + this.ex + ")");

        return sb;
    }
}
