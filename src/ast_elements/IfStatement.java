package ast_elements;

import java.util.*;

import Executor.ExecutionException;
import Executor.ReturnFromCall;
import SemanticAnalysis.SemanticAnalysisException;

public class IfStatement extends Statement {

    private Expression cond;
    private List<Statement> body;

    private List<ElifStatement> elif_stmts;

    private ElseStatement else_stmt;

    public IfStatement(Expression cond, List<Statement> body, List<ElifStatement> elif_stmts, ElseStatement e_stmt) {
        this.cond = cond;
        this.body = body;
        this.elif_stmts = elif_stmts;
        this.else_stmt = e_stmt;
    }

    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);
        StringBuilder sb = new StringBuilder();
        sb.append(ind).append("if ").append(this.cond.toString()).append(":\n");
        for (Statement stmt : this.body) {
            sb.append(stmt.toString(indent + 1));
        }
        if (elif_stmts != null) {
            for (int i = 0; i < elif_stmts.size(); i++) {
                sb.append(elif_stmts.get(i).toString(indent));
            }
        }
        if (else_stmt != null) {
            sb.append(else_stmt.toString(indent));
        }
        return sb;
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
        if (elif_stmts != null) {
            for (int i = 0; i < elif_stmts.size(); i++) {
                elif_stmts.get(i).analyze(variable_Map, func_Map);
            }
        }
        if (else_stmt != null) {
            else_stmt.analyze(variable_Map, func_Map);
        }
    }

    @Override
    public void execute(Map<String, Object> variable_Map, Map<String, FunctionDeclaration> func_Map)
            throws ExecutionException, ReturnFromCall {
        boolean runned = false;
        Boolean condition = (Boolean) cond.evaluate(variable_Map, func_Map);
        if (condition) {
            Map<String, Object> localVar_Map = new HashMap<>(variable_Map);
            Map<String, FunctionDeclaration> localFun_Map = new HashMap<String, FunctionDeclaration>(func_Map);
            
            for (Statement stmt : body) {
                stmt.execute(localVar_Map, localFun_Map);
            }
            runned = true;
        }
        if (!runned && elif_stmts != null) {
            int i = 0;
            while (i < elif_stmts.size() && (Boolean)elif_stmts.get(i).getCond().evaluate(variable_Map, func_Map)) {
                i++;
            }
            if (i < elif_stmts.size()) {
                elif_stmts.get(i).execute(variable_Map, func_Map);
                runned = true;
            }
        } 
        if (!runned && else_stmt != null) {
            else_stmt.execute(variable_Map, func_Map);
            runned = true;
        }
    }
}