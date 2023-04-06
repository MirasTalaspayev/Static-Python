package ast_elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import SemanticAnalysis.SemanticAnalysisException;

public class FunctionDeclaration extends Declaration {
    private String func_name;
    private List<LocalVarDeclaration> param_list;
    private Type return_Type;
    private List<Statement> body;

    public FunctionDeclaration(String func_name, List<LocalVarDeclaration> param_list, Type return_Type, List<Statement> body) {
        this.func_name = func_name;
        this.param_list = param_list;
        this.return_Type = return_Type;
        this.body = body;

        if (this.param_list == null)
            this.param_list = new ArrayList<LocalVarDeclaration>();
    }

    public List<LocalVarDeclaration> getParam_list() {
        return this.param_list;
    }

    public Type getReturn_Type() {
        return this.return_Type;
    }

    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);
        StringBuilder sb = new StringBuilder();
        sb.append(ind).append("def ").append(this.func_name + "(");
        int size = param_list.size();
        for (int i = 0; i < size - 1; i++)
            sb.append(param_list.get(i)).append(", ");
        if (size >= 1)
            sb.append(param_list.get(size - 1));
        sb.append(") -> " + this.return_Type + ":").append("\n");
        for (Statement stmt : this.body)
            sb.append(stmt.toString(indent + 1));
        return sb;
    }

    @Override
    public void analyze(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map) throws SemanticAnalysisException {
        boolean hasReturn = false;
        Map<String, Type> localVar_Map = new HashMap<String, Type>(variable_Map);
        Map<String, FunctionDeclaration> localFunc_Map = new HashMap<String, FunctionDeclaration>(func_Map);

        HashSet<String> names = new HashSet<String>();
        for (int i=0; i < this.param_list.size(); i++) {
            if (names.contains(param_list.get(i).getVar_name()))
                throw new SemanticAnalysisException("parameters cannot repeat");
            names.add(this.param_list.get(i).getVar_name());
        }
        
        for (int i=0; i < this.param_list.size(); i++)
            localVar_Map.put(this.param_list.get(i).getVar_name(), this.param_list.get(i).getType());

        for (Statement stmt : this.body) {
            stmt.analyze(localVar_Map, localFunc_Map);
            if (stmt instanceof Return) {
                Return return_stmt = (Return)stmt;
                if (return_Type == null) {
                    if (return_stmt.getEx() != null)
                        throw new SemanticAnalysisException("return type must be None in void function");
                } else {
                    hasReturn = true;
                    if (return_stmt.getEx() != null)
                        return_stmt.getEx().analyze(localVar_Map, localFunc_Map, return_Type);
                }
            }
        } localVar_Map = null; localFunc_Map = null;
        
        if (!hasReturn && return_Type != null)
            throw new SemanticAnalysisException("non-void function must have return");

        func_Map.put(this.func_name, this);
    }
}
