package ast_elements;

import java.util.List;
import java.util.Map;

import SemanticAnalysis.SemanticAnalysisException;

public class FunctionExpression extends Expression {

    private String func_name;
    private List<Expression> ex_list;

    public FunctionExpression(String func_name, List<Expression> ex_list) {
        super();
        this.func_name = func_name;
        this.ex_list = ex_list;
        System.out.println("ex_list === " + ex_list);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.func_name + "(");
        int size = ex_list.size();
        for (int i = 0; i < size - 1; i++) {
            sb.append(ex_list.get(i)).append(", ");
        }
        if (size >= 1) {
            sb.append(ex_list.get(size - 1) + ")");
        }
        return sb.toString();
    }

	@Override
	public Type analyzeAndGetType(Map<String, Type> variable_Map, Map<String, FunctionDeclaration> func_Map) throws SemanticAnalysisException {
		if (!func_Map.containsKey(func_name)) {
            throw new SemanticAnalysisException("function doesn't exist");
        }

        if(ex_list.size() != func_Map.get(func_name).getParam_list().size()) {
            throw new SemanticAnalysisException("number of parameters doesn't match");
        }

        for (int i=0; i < func_Map.get(func_name).getParam_list().size(); i++) {
            if (ex_list.get(i) instanceof LabelExpression) {
                if (!variable_Map.containsKey(ex_list.get(i))) {
                    throw new SemanticAnalysisException("variable " + ex_list.get(i) + " in parameters doesn't exist");
                }
            }
            if (ex_list.get(i) != null && !ex_list.get(i).analyzeAndGetType(variable_Map, func_Map).equals(func_Map.get(func_name).getParam_list().get(i).getType())) 
                throw new SemanticAnalysisException("parameter type " + ex_list.get(i) + " does not match with " + func_Map.get(func_name).getParam_list().get(i).getType());
        }

        for (Statement stmt : func_Map.get(func_name).getBody()) {
            stmt.analyze(variable_Map, func_Map);
        }
        return func_Map.get(func_name).getReturn_Type();
	}
}
