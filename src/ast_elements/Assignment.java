package ast_elements;

import java.util.Map;

import SemanticAnalysis.SemanticAnalysisException;

public class Assignment extends Statement {

    private String var_name;
    private Expression ex;

    public Assignment(String var_name, Expression ex) {
        this.var_name = var_name;
        this.ex = ex;
    }

    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);
        StringBuilder sb = new StringBuilder();
        sb.append(ind).append(this.var_name + " = ").append(this.ex).append("\n");
        return sb;
    }

    @Override
    public void analyze(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map) throws SemanticAnalysisException {
        if (!variable_Map.containsKey(var_name)) {
            throw new SemanticAnalysisException("Variable " + var_name + " does not exist");
        }
        ex.analyze(variable_Map, func_Map, variable_Map.get(var_name));
    }
}