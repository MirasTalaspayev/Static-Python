package ast_elements;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import SemanticAnalysis.SemanticAnalysisException;

public class FunctionCall extends Expression {

    private String func_name;
    private List<Expression> ex_list;
    
    public FunctionCall(String func_name, List<Expression> ex_list) {
        this.func_name = func_name;
        this.ex_list = ex_list;
    }

    public String getFunc_name() {
        return func_name;
    }

    public List<Expression> getEx_list() {
        return ex_list;
    }

    public StringBuilder toString(int indent) {
        String ind = IndentUtil.indentStr(indent);
        StringBuilder sb = new StringBuilder();
        sb.append(ind).append(this.func_name + "(");
        int size = ex_list.size();
        for (int i = 0; i < size - 1; i++) {
            sb.append(ex_list.get(i)).append(", ");
        }
        if (size >= 1) {
            sb.append(ex_list.get(size - 1) + ")\n");
        }
        return sb;
    }

    @Override
    public String toString() {
        return toString(0).toString();
    }

    @Override
    public Type analyzeAndGetType(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map) throws SemanticAnalysisException {
        if (!func_Map.containsKey(func_name))
            throw new SemanticAnalysisException("function doesn't exist");

        if(ex_list.size() != func_Map.get(func_name).getParam_list().size())
            throw new SemanticAnalysisException("number of parameters doesn't match");

        for (int i=0; i < func_Map.get(func_name).getParam_list().size(); i++) {
            if (ex_list.get(i) != null && !ex_list.get(i).analyzeAndGetType(variable_Map, func_Map).equals(func_Map.get(func_name).getParam_list().get(i).getType())) 
                throw new SemanticAnalysisException("parameter type " + ex_list.get(i) + " does not match with " + func_Map.get(func_name).getParam_list().get(i).getType());
        }
        return func_Map.get(func_name).getReturn_Type();
    }
}