package ast_elements;

import java.util.List;
import java.util.Map;

import SemanticAnalysis.SemanticAnalysisException;

public class IfStatement extends Statement {

    private Expression cond;
    private List<Statement> body;

    public IfStatement(Expression cond, List<Statement> body) {
        this.cond = cond;
        this.body = body;
    }

    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);
        StringBuilder sb = new StringBuilder();
        sb.append(ind).append("if ").append(this.cond.toString()).append(":\n");
        for (Statement stmt : this.body) {
            sb.append(stmt.toString(indent + 1));
        }
        return sb;
    }

    @Override
    public void analyze(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map) throws SemanticAnalysisException {
        if (!(cond instanceof BooleanExpression)) {
            throw new SemanticAnalysisException("It is not boolean");
        }

        for (Statement stmt : body) {
            stmt.analyze(variable_Map, func_Map);
        }
    }
}
