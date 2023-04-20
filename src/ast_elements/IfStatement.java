package ast_elements;

import java.util.*;
import SemanticAnalysis.SemanticAnalysisException;

public class IfStatement extends Statement {

    private Expression cond;
    private List<Statement> body;

    private List<ElifStatement> elif_stmts;

    private ElseStatement e_stmt;

    public IfStatement(Expression cond, List<Statement> body, List<ElifStatement> elif_stmts, ElseStatement e_stmt) {
        this.cond = cond;
        this.body = body;
        this.elif_stmts = elif_stmts;
        this.e_stmt = e_stmt;
        System.out.println("CONDITION === " + cond);
    }

    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);
        StringBuilder sb = new StringBuilder();
        sb.append(ind).append("if ").append(this.cond.toString()).append(":\n");
        for (Statement stmt : this.body) {
            sb.append(stmt.toString(indent + 1));
        }
        if(elif_stmts != null){
            for(int i=0; i<elif_stmts.size(); i++){
                sb.append(elif_stmts.get(i).toString(indent));
            }
        }
        if(e_stmt != null){
            sb.append(e_stmt.toString(indent));
        }
        return sb;
    }

    @Override
    public void analyze(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map) throws SemanticAnalysisException {
        cond.analyze(variable_Map, func_Map, BooleanExpression.TYPE);
        
        Map<String, Type> localVar_Map = new HashMap<String, Type>(variable_Map);
        Map<String, FunctionDeclaration> localFun_Map = new HashMap<String, FunctionDeclaration>(func_Map);
        for (Statement stmt : body) {
            stmt.analyze(localVar_Map, localFun_Map);
        }
        localVar_Map = null;
        localFun_Map = null;
        if(elif_stmts != null){
            for(int i=0; i<elif_stmts.size(); i++){
                localVar_Map = new HashMap<String, Type>(variable_Map);
                localFun_Map = new HashMap<String, FunctionDeclaration>(func_Map);
                elif_stmts.get(i).analyze(localVar_Map, localFun_Map);
            }
        }
        if(e_stmt != null){
            localVar_Map = new HashMap<String, Type>(variable_Map);
            localFun_Map = new HashMap<String, FunctionDeclaration>(func_Map);
            e_stmt.analyze(localVar_Map, localFun_Map);
        }
    }
}