package ast_elements;

import java.util.List;
import java.util.Map;

import SemanticAnalysis.SemanticAnalysisException;

public class ForLoop extends Statement {
    private String var_name;
    private String var_type;
    private Expression list;
    private List<Statement> body;

    public ForLoop(String var_name, String var_type, Expression list, List<Statement> body) {
        this.var_name = var_name;
        this.var_type = var_type;
        this.list = list;
        this.body = body;
    }

    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);
        StringBuilder sb = new StringBuilder();
        sb.append(ind).append("for ").append(var_name + ": " + var_type).append(" in ").append(this.list.toString()).append(":\n");
        for (Statement stmt : this.body) {
            sb.append(stmt.toString(indent + 1));
        }
        return sb;
    }

    @Override
    public void analyze(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map) throws SemanticAnalysisException {
        if (!(list.analyzeAndGetType(variable_Map, func_Map) instanceof CollectionType)) {
            throw new SemanticAnalysisException("It is not boolean");
        }
    }
}
