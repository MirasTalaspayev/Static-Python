package ast_elements;

import java.util.*;

import Executor.ExecutionException;
import Executor.ReturnFromCall;
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
        sb.append(ind).append("for ").append(var_name + ": " + var_type).append(" in ").append(this.list.toString())
                .append(":\n");
        for (Statement stmt : this.body) {
            sb.append(stmt.toString(indent + 1));
        }
        return sb;
    }

    @Override
    public void analyze(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map)
            throws SemanticAnalysisException {
        if (!(list.analyzeAndGetType(variable_Map, func_Map) instanceof CollectionType)) {
            throw new SemanticAnalysisException(list + " is not of collection type");
        }
        CollectionExpressions ce = (CollectionExpressions) list;
        if (!(ce.getElementType().equals(var_type))) {
            throw new SemanticAnalysisException(var_name + " should be of type " + ce.getElementType());
        }
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
        Object collection = list.evaluate(variable_Map, func_Map);
        System.out.println(collection);
        if (collection instanceof ArrayList) {
            ArrayList<Object> list = (ArrayList<Object>) collection;
            for (int i = 0; i < list.size(); i++) {
                localVar_Map.put(var_name, list.get(i));
                for (Statement stmt : body) {
                    stmt.execute(localVar_Map, localFun_Map);
                }
            }
        }
    }
}
