package ast_elements;

import java.util.*;
import SemanticAnalysis.SemanticAnalysisException;

public class ElseStatement extends Statement {
    private List<Statement> body;

    public ElseStatement(List<Statement> body) {
        this.body = body;
    }

    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);
        StringBuilder sb = new StringBuilder();
        sb.append(ind).append("else ").append(":\n");
        for (Statement stmt : this.body) {
            sb.append(stmt.toString(indent + 1));
        }
        return sb;
    }

    @Override
    public void analyze(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map) throws SemanticAnalysisException {
        Map<String, Type> localVar_Map = new HashMap<String, Type>(variable_Map);
        Map<String, FunctionDeclaration> localFun_Map = new HashMap<String, FunctionDeclaration>(func_Map);
        for (Statement stmt : body) {
            stmt.analyze(localVar_Map, localFun_Map);
        }
        localVar_Map = null;
        localFun_Map = null;
    }
}