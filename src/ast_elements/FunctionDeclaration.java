package ast_elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

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

        System.out.println("Function Name === " + this.func_name);
        System.out.println("param_list === " + this.param_list);
    }

    public String getFunc_name() {
        return this.func_name;
    }

    public List<LocalVarDeclaration> getParam_list() {
        return this.param_list;
    }

    public Type getReturn_Type() {
        return this.return_Type;
    }

    public List<Statement> getBody() {
        return this.body;
    }

    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);
        StringBuilder sb = new StringBuilder();
        sb.append(ind).append("def ").append(this.func_name + "(");
        int size = param_list.size();
        for (int i = 0; i < size - 1; i++) {
            sb.append(param_list.get(i)).append(", ");
        }
        if (size >= 1) {
            sb.append(param_list.get(size - 1));
        }
        sb.append(") -> " + this.return_Type + ":").append("\n");
        for (Statement stmt : this.body) {
            sb.append(stmt.toString(indent + 1));
        }
        return sb;
    }

    @Override
    public void analyze(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map) throws SemanticAnalysisException {
        HashSet<String> names = new HashSet<String>();
        for (int i=0; i < this.param_list.size(); i++) {
            if (names.contains(param_list.get(i).getVar_name()))
                throw new SemanticAnalysisException("parameters cannot repeat");
            names.add(this.param_list.get(i).getVar_name());
        }

        Map<String, Type> localVar_Map = new HashMap<String, Type>(variable_Map);

        for (int i=0; i < this.param_list.size(); i++) {
            localVar_Map.put(this.param_list.get(i).getVar_name(), this.param_list.get(i).getType());
        }

        boolean hasReturn = false;
        for (Statement stmt : this.body) {
            stmt.analyze(localVar_Map, func_Map);
            if (stmt instanceof Return) {
                Return return_stmt = (Return)stmt;
                if (return_Type == null) {
                    if (return_stmt.getEx() != null) {
                        throw new SemanticAnalysisException("return type must be None in void function");
                    }
                } else {
                    hasReturn = true;
                    if ((!return_stmt.getEx().analyzeAndGetType(localVar_Map, func_Map).equals(return_Type)) || (return_stmt.getEx() != null)) {
                        throw new SemanticAnalysisException("return type " + return_stmt.getEx() + " does not match with " + return_Type);
                    }
                }
            }
        }
        if (!hasReturn) {
            throw new SemanticAnalysisException("non-void function must have return");
        }

        // localVar_Map = null;
        func_Map.put(this.func_name, this);
    }
}
