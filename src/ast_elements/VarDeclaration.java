package ast_elements;

import java.util.Map;

import SemanticAnalysis.SemanticAnalysisException;

public class VarDeclaration extends Declaration {
    private String var_name;
    private Type type;
    private Expression ex;

    public VarDeclaration(String var_name, Type type, Expression ex) {
        this.var_name = var_name;
        this.type = type;
        this.ex = ex;
    }

    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);
        StringBuilder sb = new StringBuilder();
        sb.append(ind).append(this.var_name + ":").append(this.type + " = ").append(this.ex).append("\n");
        return sb;
    }

    @Override
    public void analyze(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map) throws SemanticAnalysisException {
        if (variable_Map.containsKey(var_name)) {
            throw new SemanticAnalysisException("variable name already exists");
        }
        if (ex == null) {
            return;
        }
        ex.analyze(variable_Map, func_Map, type);

        
        variable_Map.put(var_name, type);
    }
}