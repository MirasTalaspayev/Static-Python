package ast_elements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        List<String> names = new ArrayList<String>();
        for (int i=0; i < this.param_list.size(); i++) {
            names.add(this.param_list.get(i).getVar_name());
        }
        Set<String> uniques = new HashSet<String>(names);
        if (uniques.size() < names.size()) {
            throw new SemanticAnalysisException("parameters cannot repeat");
        }
        func_Map.put(this.func_name, this);
    }
}
