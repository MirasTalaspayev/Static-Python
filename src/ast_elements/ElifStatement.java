package ast_elements;

import java.util.*;

import Executor.ExecutionException;
import Executor.ReturnFromCall;
import SemanticAnalysis.SemanticAnalysisException;

public class ElifStatement extends Statement {
    private Expression cond;
    private List<Statement> body;

    public ElifStatement(Expression cond, List<Statement> body) {
        this.cond = cond;
        this.body = body;
        System.out.println("CONDITION === " + cond);
    }

    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);
        StringBuilder sb = new StringBuilder();
        sb.append(ind).append("elif ").append(this.cond.toString()).append(":\n");
        for (Statement stmt : this.body) {
            sb.append(stmt.toString(indent + 1));
        }
        return sb;
    }

    public Expression getCond() {
        return cond;
    }

    @Override
    public void analyze(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map)
            throws SemanticAnalysisException {
        cond.analyze(variable_Map, func_Map, BooleanExpression.TYPE);

        Map<String, Type> localVar_Map = new HashMap<String, Type>(variable_Map);
        Map<String, FunctionDeclaration> localFun_Map = new HashMap<String, FunctionDeclaration>(func_Map);
        for (Statement stmt : body) {
            stmt.analyze(localVar_Map, localFun_Map);
        }
        localVar_Map = null;
        localFun_Map = null;
    }

    @Override
    public void execute(Map<String, Object> variable_Map, Map<String, FunctionDeclaration> func_Map)
            throws ExecutionException, ReturnFromCall {
        Map<String, Object> localVar_Map = new HashMap<>(variable_Map);
        Map<String, FunctionDeclaration> localFun_Map = new HashMap<String, FunctionDeclaration>(func_Map);
        
        for (Statement stmt : body) {
            stmt.execute(localVar_Map, localFun_Map);
        }
    }
}