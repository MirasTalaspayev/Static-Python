package ast_elements;

import java.util.List;

public class ProgramAST {

    private List<Statement> stmts;

    public ProgramAST(List<Statement> stmts) {
        this.stmts = stmts;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Statement stmt : stmts) {
            sb.append(stmt.toString(0));
        }
        return sb.toString();
    }

}
